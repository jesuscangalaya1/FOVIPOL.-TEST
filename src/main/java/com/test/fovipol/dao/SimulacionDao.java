package com.test.fovipol.dao;

import com.test.fovipol.dto.SimulacionDto;
import com.test.fovipol.dto.response.SimulacionResponse;

import java.util.List;

public interface SimulacionDao {

    boolean guardarSimulacion(SimulacionDto simulacion);

    List<SimulacionResponse> getSimulaciones();
}
