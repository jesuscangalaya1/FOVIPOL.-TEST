package com.test.fovipol.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.sql.Timestamp;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocioDto {
    @Hidden
    private String codigo;

    @NotBlank(message = "El apellido paterno no debe estar en blanco")
    @Size(max = 100, min = 2, message = "El apellido paterno debe tener como máximo {max} caracteres y como mínimo {min} caracteres.")
    @Schema(description = "Apellido Paterno del socio", example = "Perez")
    private String apePaterno;

    @Schema(description = "Apellido Materno del socio", example = "Gomez")
    private String apeMaterno;

    @NotBlank(message = "El nombre no debe estar en blanco")
    @Size(max = 100, min = 2, message = "El nombre debe tener como máximo {max} caracteres y como mínimo {min} caracteres.")
    @Schema(description = "Nombre del socio", example = "Juan")
    private String nombres;

    @Hidden
    private String nombresCompletos;

    @Schema(description = "Tipo de Documento del socio", example = "1")
    private String tipoDocumento;

    @Schema(description = "Número de Documento del socio", example = "12345678")
    private String numeroDocumento;

    @Email(message = "El correo debe tener un formato correcto")
    @Schema(description = "Correo del socio", example = "juan.perez@gmail.com")
    private String correo;

    @Hidden
    private String usuarioCrea;

    @Hidden
    private Timestamp fechaCrea;
    @Hidden
    private int flag;


}
