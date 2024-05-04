package com.test.fovipol.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimulacionDto {


    @Schema(defaultValue = "1")
    private int entidadPago;
    private String codigoSocio;

    @Schema(defaultValue = "1")
    private int idSeguro;

    @NotNull(message = "La remuneración es obligatoria")
    @DecimalMin(value = "0.0", inclusive = false, message = "La remuneración debe ser mayor que cero")
    @Schema(description = "remuneracion ", example = "1976")
    private BigDecimal renumeracion;
    private BigDecimal descuentoOficial;
    private BigDecimal descuentoPersonal;
    private BigDecimal bonificacion;

    @Hidden
    private BigDecimal monto;

    @Hidden
    private Timestamp fechaCrea;

    @Hidden
    private String usuarioCrea;

    @Hidden
    private int flag;

}
