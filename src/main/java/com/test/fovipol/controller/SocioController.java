package com.test.fovipol.controller;

import com.test.fovipol.dto.SocioDto;
import com.test.fovipol.dto.response.ServiceResponse;
import com.test.fovipol.service.SocioService;
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
@RequestMapping("/api/socios")
@RequiredArgsConstructor
@Tag(name = "SOCIO", description = "Operaciones permitidas sobre la entidad SOCIO")
public class SocioController {

    private final SocioService socioService;


    @Operation(summary = "Crear un nuevo socio")
    @ApiResponse(responseCode = "201", description = "Socio creado exitosamente")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse> createClient(@RequestBody @Valid SocioDto socioDto) {

        SocioDto clientResponse = socioService.saveSocio(socioDto);
        return new ResponseEntity<>(ServiceResponse.builder()
                .mensaje("Socio creado exitosamente")
                .codigo(String.valueOf(HttpStatus.CREATED))
                .resultado(clientResponse)
                .build(),
                HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Listar socios")
    @ApiResponse(responseCode = "200", description = "Socio listado exitosamente")
    public ResponseEntity<ServiceResponse> listarSocios() {
        List<SocioDto> socios = socioService.getSocios();
        return new ResponseEntity<>(ServiceResponse.builder()
                .mensaje("Lista de socios exitosamente")
                .codigo(String.valueOf(HttpStatus.OK))
                .resultado(socios)
                .build(),
                HttpStatus.OK);
    }


}
