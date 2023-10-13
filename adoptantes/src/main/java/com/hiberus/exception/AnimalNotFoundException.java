package com.hiberus.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnimalNotFoundException extends RuntimeException {
    private Integer id;
}
