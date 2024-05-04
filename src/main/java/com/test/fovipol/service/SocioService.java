package com.test.fovipol.service;

import com.test.fovipol.dto.SocioDto;

import java.util.List;

public interface SocioService {

    SocioDto saveSocio(SocioDto socioDto);

    List<SocioDto> getSocios();

}
