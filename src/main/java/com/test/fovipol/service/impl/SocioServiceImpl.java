package com.test.fovipol.service.impl;

import com.test.fovipol.dao.SocioDao;
import com.test.fovipol.dto.SocioDto;
import com.test.fovipol.exceptions.BusinessException;
import com.test.fovipol.service.SocioService;
import com.test.fovipol.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SocioServiceImpl implements SocioService {

    private final SocioDao socioDao;

    @Override
    @Transactional
    public SocioDto saveSocio(SocioDto socio) {
        // Genera código alfanumérico
        String codigo = generateCodigo(socio.getApePaterno(), socio.getApeMaterno(), socio.getNombres());
        socio.setCodigo(codigo);

        // Concatenar apellidos y nombres
        String nombresCompletos = socio.getApePaterno() + " " + socio.getApeMaterno() + " " + socio.getNombres();
        socio.setNombresCompletos(nombresCompletos);

        // Validar tipo de documento
        if (socio.getTipoDocumento().equals("1")) {
            socio.setTipoDocumento("DNI");
        } else if (socio.getTipoDocumento().equals("2")) {
            socio.setTipoDocumento("CE");
        } else {
            throw new BusinessException(Constants.DOC_TYPE_ERR, HttpStatus.BAD_REQUEST, Constants.INVALID_DOC_TYPE );
        }

        // Validar formato de correo
        if (!socio.getCorreo().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new BusinessException(Constants.EMAIL_FORMAT_ERR, HttpStatus.BAD_REQUEST, Constants.INVALID_EMAIL_FORMAT );
        }

        socio.setUsuarioCrea(socio.getNombres());
        socio.setFechaCrea(new java.sql.Timestamp(new Date().getTime()));
        socio.setFlag(1);

        boolean result = socioDao.saveSocio(socio);

        if (result) {
            return SocioDto.builder()
                    .codigo(socio.getCodigo())
                    .apePaterno(socio.getApePaterno())
                    .apeMaterno(socio.getApeMaterno())
                    .nombres(socio.getNombres())
                    .nombresCompletos(socio.getNombresCompletos())
                    .tipoDocumento(socio.getTipoDocumento())
                    .numeroDocumento(socio.getNumeroDocumento())
                    .correo(socio.getCorreo())
                    .usuarioCrea(socio.getUsuarioCrea())
                    .fechaCrea(socio.getFechaCrea())
                    .flag(socio.getFlag())
                    .build();
        } else {
            throw new BusinessException(Constants.SAVE_CLIENT_ERR, HttpStatus.INTERNAL_SERVER_ERROR, Constants.FAILED_TO_SAVE_CLIENT);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<SocioDto> getSocios() {
        return socioDao.getSocios();
    }


    private String generateCodigo(String apePaterno, String apeMaterno, String nombres) {
        String codigo = "SOC";
        codigo += apePaterno.toUpperCase().substring(0, 1);
        if (apeMaterno != null && !apeMaterno.isEmpty()) {
            codigo += apeMaterno.toUpperCase().substring(0, 1);
        } else {
            codigo += apePaterno.toUpperCase().substring(1, 2);
        }
        codigo += nombres.toUpperCase().substring(0, 1);
        codigo += "-" + String.format("%07d", 1);
        return codigo;
    }

}
