package com.hiberus.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdoptanteNotFoundException extends RuntimeException {
    private Integer id;
}
