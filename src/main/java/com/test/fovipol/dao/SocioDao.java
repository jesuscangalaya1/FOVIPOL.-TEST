package com.test.fovipol.dao;

import com.test.fovipol.dto.SocioDto;

import java.util.List;

public interface SocioDao {

    boolean saveSocio(SocioDto socioDto);

    List<SocioDto> getSocios();

}
