package com.test.fovipol.service;

import com.test.fovipol.dto.SimulacionDto;
import com.test.fovipol.dto.response.SimulacionResponse;

import java.util.List;

public interface SimulacionService {

    SimulacionDto guardarSimulacion(SimulacionDto simulacion);

    List<SimulacionResponse> getSimulaciones();
}
