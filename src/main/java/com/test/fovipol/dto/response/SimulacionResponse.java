package com.test.fovipol.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class SimulacionResponse {

    private int id;
    private int entidadPago;
    private String codigoSocio;
    private int idSeguro;
    private BigDecimal renumeracion;
    private BigDecimal descuentoOficial;
    private BigDecimal descuentoPersonal;
    private BigDecimal bonificacion;
    private BigDecimal monto;
    private Timestamp fechaCrea;
    private String usuarioCrea;
    private int flag;

}
