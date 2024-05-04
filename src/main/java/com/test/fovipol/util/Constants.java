package com.test.fovipol.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

/**
 * la clase AppConstants es una clase de utilidad que contiene constantes de la aplicación.
 * @UtilityClass: Anotación de Lombok que permite crear una clase con métodos estáticos, sin necesidad de instanciar la clase.
 */
@UtilityClass
public class Constants {
    // Error codes
    public static final String SOCIO_CODE_ERR = "SOCIO_CODE_ERR";
    public static final String SEGURO_ID_ERR = "SEGURO_ID_ERR";
    public static final String PAYMENT_ENTITY_ERR = "PAYMENT_ENTITY_ERR";
    public static final String MEMBER_CODE_ERR = "MEMBER_CODE_ERR";
    public static final String SOCIO_NAME_ERR = "SOCIO_NAME_ERR";
    public static final String SAVE_SIMULATION_ERR = "SAVE_SIMULATION_ERR";

    // Error messages
    public static final String INVALID_SOCIO_CODE = "Invalid socio code";
    public static final String INVALID_SEGURO_ID = "Invalid seguro id";
    public static final String INVALID_PAYMENT_ENTITY = "Invalid payment entity";
    public static final String INVALID_MEMBER_CODE = "Invalid member code";
    public static final String SOCIO_NAME_NOT_FOUND = "Socio name not found";
    public static final String FAILED_TO_SAVE_SIMULATION = "Failed to save simulation";

    // Calculation constants
    public static final BigDecimal DIECO_PERCENTAGE = BigDecimal.valueOf(0.3);
    public static final BigDecimal CAJA_PERCENTAGE = BigDecimal.valueOf(0.5);

    // Error codes
    public static final String DOC_TYPE_ERR = "DOC_TYPE_ERR";
    public static final String EMAIL_FORMAT_ERR = "EMAIL_FORMAT_ERR";
    public static final String SAVE_CLIENT_ERR = "SAVE_CLIENT_ERR";

    // Error messages
    public static final String INVALID_DOC_TYPE = "Invalid document type";
    public static final String INVALID_EMAIL_FORMAT = "Invalid email format";
    public static final String FAILED_TO_SAVE_CLIENT = "Failed to save client";

}