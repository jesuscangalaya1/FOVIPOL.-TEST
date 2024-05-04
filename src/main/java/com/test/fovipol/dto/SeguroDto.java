package com.test.fovipol.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeguroDto {

    private int id;
    private String nombre;
    private BigDecimal tasa;
}
