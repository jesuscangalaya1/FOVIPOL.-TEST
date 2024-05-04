package com.test.fovipol.controller;

import com.test.fovipol.dto.SeguroDto;
import com.test.fovipol.dto.response.ServiceResponse;
import com.test.fovipol.service.SeguroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/seguros")
@RequiredArgsConstructor
@Tag(name = "SEGURO", description = "Operaciones permitidas sobre la entidad SEGURO")
public class SeguroController {

    private final SeguroService seguroService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Listar Seguros")
    @ApiResponse(responseCode = "200", description = "Seguros listado exitosamente")
    public ResponseEntity<ServiceResponse> listarSeguros() {
        List<SeguroDto> seguros = seguroService.getSeguros();
        return new ResponseEntity<>(ServiceResponse.builder()
                .mensaje("Lista de SEGUROS exitosamente")
                .codigo(String.valueOf(HttpStatus.OK))
                .resultado(seguros)
                .build(),
                HttpStatus.OK);
    }

}
