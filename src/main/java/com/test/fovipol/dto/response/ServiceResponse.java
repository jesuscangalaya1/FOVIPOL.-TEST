package com.test.fovipol.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse  {
    private String mensaje;
    private String codigo;
    private Object resultado;


}
