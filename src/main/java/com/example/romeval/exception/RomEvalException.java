package com.example.romeval.exception;

import lombok.Getter;

/**
 * Sebastian Gruchot created on 02.03.2023
 */
@Getter
public class RomEvalException
    extends RuntimeException {

    private final String message;
    public RomEvalException(String message) {
        this.message = message;
    }
}
