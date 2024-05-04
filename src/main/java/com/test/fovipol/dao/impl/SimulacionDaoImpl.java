package com.test.fovipol.dao.impl;

import com.test.fovipol.dao.SimulacionDao;
import com.test.fovipol.dto.SimulacionDto;
import com.test.fovipol.dto.response.SimulacionResponse;
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
public class SimulacionDaoImpl implements SimulacionDao {

    private final DataSource dataSource;

    @Override
    public boolean guardarSimulacion(SimulacionDto simulacion) {
        try (Connection connection = dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall("{call GuardarSimulacion(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {
            stmt.setInt(1, simulacion.getEntidadPago());
            stmt.setString(2, simulacion.getCodigoSocio());
            stmt.setInt(3, simulacion.getIdSeguro());
            stmt.setBigDecimal(4, simulacion.getRenumeracion());
            stmt.setBigDecimal(5, simulacion.getDescuentoOficial());
            stmt.setBigDecimal(6, simulacion.getDescuentoPersonal());
            stmt.setBigDecimal(7, simulacion.getBonificacion());
            stmt.setBigDecimal(8, simulacion.getMonto());
            stmt.setDate(9, new java.sql.Date(simulacion.getFechaCrea().getTime()));
            stmt.setString(10, simulacion.getUsuarioCrea());
            stmt.setInt(11, simulacion.getFlag());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<SimulacionResponse> getSimulaciones() {
        List<SimulacionResponse> simulaciones = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall("{call ListarSimulaciones()}")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SimulacionResponse simulacion = SimulacionResponse.builder()
                        .id(rs.getInt("id"))
                        .entidadPago(rs.getInt("entidad_pago"))
                        .codigoSocio(rs.getString("codigo_socio"))
                        .idSeguro(rs.getInt("id_seguro"))
                        .renumeracion(rs.getBigDecimal("renumeracion"))
                        .descuentoOficial(rs.getBigDecimal("descuento_oficial"))
                        .descuentoPersonal(rs.getBigDecimal("descuento_personal"))
                        .bonificacion(rs.getBigDecimal("bonificacion"))
                        .monto(rs.getBigDecimal("monto"))
                        .fechaCrea(rs.getTimestamp("fecha_crea"))
                        .usuarioCrea(rs.getString("usuario_crea"))
                        .flag(rs.getInt("flag"))
                        .build();
                simulaciones.add(simulacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return simulaciones;
    }

}
