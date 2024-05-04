package com.test.fovipol.dao.impl;

import com.test.fovipol.dao.SocioDao;
import com.test.fovipol.dto.SocioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SocioDaoImpl implements SocioDao {

    private final DataSource dataSource;

    @Override
    public boolean saveSocio(SocioDto socio) {
        boolean result = false;
        try (Connection connection = dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall("{call InsertSocio(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {
            connection.setAutoCommit(false);
            stmt.setString(1, socio.getCodigo());
            stmt.setString(2, socio.getApePaterno());
            stmt.setString(3, socio.getApeMaterno());
            stmt.setString(4, socio.getNombres());
            stmt.setString(5, socio.getNombresCompletos());
            stmt.setString(6, socio.getTipoDocumento());
            stmt.setString(7, socio.getNumeroDocumento());
            stmt.setString(8, socio.getCorreo());
            stmt.setString(9, socio.getUsuarioCrea());
            stmt.setTimestamp(10, new java.sql.Timestamp(socio.getFechaCrea().getTime()));
            stmt.setInt(11, socio.getFlag());
            stmt.execute();

            connection.commit();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<SocioDto> getSocios() {
        List<SocioDto> socios = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall("{call ListarSocios()}")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SocioDto socio = SocioDto.builder()
                        .codigo(rs.getString("codigo"))
                        .apePaterno(rs.getString("ape_paterno"))
                        .apeMaterno(rs.getString("ape_materno"))
                        .nombres(rs.getString("nombres"))
                        .nombresCompletos(rs.getString("nombres_completos"))
                        .tipoDocumento(rs.getString("tipo_documento"))
                        .numeroDocumento(rs.getString("numero_documento"))
                        .correo(rs.getString("correo"))
                        .usuarioCrea(rs.getString("usuario_crea"))
                        .fechaCrea(rs.getTimestamp("fecha_crea"))
                        .flag(rs.getInt("flag"))
                        .build();
                socios.add(socio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return socios;
    }
}
