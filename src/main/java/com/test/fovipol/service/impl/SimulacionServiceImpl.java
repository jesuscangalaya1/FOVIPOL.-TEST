package com.test.fovipol.service.impl;

import com.test.fovipol.dao.SimulacionDao;
import com.test.fovipol.dto.SeguroDto;
import com.test.fovipol.dto.SimulacionDto;
import com.test.fovipol.dto.SocioDto;
import com.test.fovipol.dto.response.SimulacionResponse;
import com.test.fovipol.exceptions.BusinessException;
import com.test.fovipol.service.SeguroService;
import com.test.fovipol.service.SimulacionService;
import com.test.fovipol.service.SocioService;
import com.test.fovipol.util.Constants;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SimulacionServiceImpl implements SimulacionService {

    private final SimulacionDao simulacionDao;
    private final SocioService socioService;
    private final SeguroService seguroService;

    @Override
    @Transactional
    public SimulacionDto guardarSimulacion(SimulacionDto simulacion) {
        // lista de socios y seguros
        List<SocioDto> socios = socioService.getSocios();
        List<SeguroDto> seguros = seguroService.getSeguros();

        // Comprobar si el código socio existe
        boolean socioExists = socios.stream().anyMatch(socio -> socio.getCodigo().equals(simulacion.getCodigoSocio()));
        if (!socioExists) {
            throw new BusinessException(Constants.SOCIO_CODE_ERR, HttpStatus.BAD_REQUEST, Constants.INVALID_SOCIO_CODE);
        }

        // Comprobar si existe el ID del seguro
        boolean seguroExists = seguros.stream().anyMatch(seguro -> seguro.getId() == simulacion.getIdSeguro());
        if (!seguroExists) {
            throw new BusinessException(Constants.SEGURO_ID_ERR, HttpStatus.BAD_REQUEST, Constants.INVALID_SEGURO_ID);
        }

        // Calcular la remuneración neta
        BigDecimal remuneracionNeta = simulacion.getRenumeracion().subtract(simulacion.getDescuentoOficial()).add(simulacion.getBonificacion());

        // Calcular los importes para DIECO y CAJA
        BigDecimal montoDieco = remuneracionNeta.multiply(Constants.DIECO_PERCENTAGE);
        BigDecimal montoCaja = remuneracionNeta.multiply(Constants.CAJA_PERCENTAGE).subtract(simulacion.getDescuentoPersonal());

        // Establecer el monto al mínimo de los dos montos calculados
        simulacion.setMonto(montoDieco.min(montoCaja));

        // Validar la entidad de pago
        if (simulacion.getEntidadPago() != 1 && simulacion.getEntidadPago() != 2) {
            throw new BusinessException(Constants.PAYMENT_ENTITY_ERR, HttpStatus.BAD_REQUEST, Constants.INVALID_PAYMENT_ENTITY);
        }

        // Validar el código de socio
        if (simulacion.getCodigoSocio().isEmpty()) {
            throw new BusinessException(Constants.MEMBER_CODE_ERR, HttpStatus.BAD_REQUEST, Constants.INVALID_MEMBER_CODE);
        }

        // Obtiene el nombre del socio según el código de socio
        String socioName = socios.stream()
                .filter(socio -> socio.getCodigo().equals(simulacion.getCodigoSocio()))
                .findFirst()
                .map(SocioDto::getNombres)
                .orElseThrow(() -> new BusinessException(Constants.SOCIO_NAME_ERR, HttpStatus.BAD_REQUEST, Constants.SOCIO_NAME_NOT_FOUND));

        simulacion.setUsuarioCrea(socioName);
        simulacion.setFechaCrea(new java.sql.Timestamp(new Date().getTime()));
        simulacion.setFlag(1);

        //Guardar la simulación
        boolean result = simulacionDao.guardarSimulacion(simulacion);

        if (result) {
            return SimulacionDto.builder()
                    .entidadPago(simulacion.getEntidadPago())
                    .codigoSocio(simulacion.getCodigoSocio())
                    .idSeguro(simulacion.getIdSeguro())
                    .renumeracion(simulacion.getRenumeracion())
                    .descuentoOficial(simulacion.getDescuentoOficial())
                    .descuentoPersonal(simulacion.getDescuentoPersonal())
                    .bonificacion(simulacion.getBonificacion())
                    .monto(simulacion.getMonto())
                    .fechaCrea(simulacion.getFechaCrea())
                    .usuarioCrea(simulacion.getUsuarioCrea())
                    .flag(simulacion.getFlag())
                    .build();
        } else {
            throw new BusinessException(Constants.SAVE_SIMULATION_ERR, HttpStatus.INTERNAL_SERVER_ERROR, Constants.FAILED_TO_SAVE_SIMULATION);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<SimulacionResponse> getSimulaciones() {
        return simulacionDao.getSimulaciones();
    }

}
