package com.test.fovipol.dao.impl;

import com.test.fovipol.dao.SeguroDao;
import com.test.fovipol.dto.SeguroDto;
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
public class SeguroDaoImpl implements SeguroDao {

    private final DataSource dataSource;

    @Override
    public List<SeguroDto> getSeguros() {
        List<SeguroDto> seguros = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall("{call ListarSeguros()}")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SeguroDto seguro = new SeguroDto();
                seguro.setId(rs.getInt("id"));
                seguro.setNombre(rs.getString("nombre"));
                seguro.setTasa(rs.getBigDecimal("tasa"));
                seguros.add(seguro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seguros;
    }
}
