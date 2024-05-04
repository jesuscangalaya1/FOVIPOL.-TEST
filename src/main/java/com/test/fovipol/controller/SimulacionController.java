package com.test.fovipol.controller;

import com.test.fovipol.dto.SimulacionDto;
import com.test.fovipol.dto.response.ServiceResponse;
import com.test.fovipol.dto.response.SimulacionResponse;
import com.test.fovipol.service.SimulacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/simulaciones")
@RequiredArgsConstructor
@Tag(name = "SIMULACION", description = "Operaciones permitidas sobre la entidad SIMULACION")
public class SimulacionController {

    private final SimulacionService simulacionService;


    @Operation(summary = "Crear un nuevo SIMULACION")
    @ApiResponse(responseCode = "201", description = "SIMULACION creado exitosamente")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse> createClient(@RequestBody @Valid SimulacionDto simulacionDto) {

        SimulacionDto simulacion = simulacionService.guardarSimulacion(simulacionDto);
        return new ResponseEntity<>(ServiceResponse.builder()
                .mensaje("SIMULACION creado exitosamente")
                .codigo(String.valueOf(HttpStatus.CREATED))
                .resultado(simulacion)
                .build(),
                HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Listar Simulaciones")
    @ApiResponse(responseCode = "200", description = "Simulaciones listado exitosamente")
    public ResponseEntity<ServiceResponse> listarSimulaciones() {
        List<SimulacionResponse> simulaciones = simulacionService.getSimulaciones();
        return new ResponseEntity<>(ServiceResponse.builder()
                .mensaje("Lista de SIMULACIONES exitosamente")
                .codigo(String.valueOf(HttpStatus.OK))
                .resultado(simulaciones)
                .build(),
                HttpStatus.OK);
    }

}
