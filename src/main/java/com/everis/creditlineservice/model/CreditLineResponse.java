package com.everis.creditlineservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class CreditLineResponse {
    private String message;
    private HttpStatus status;
    private Map<String, Object> body;
}
