package com.everis.creditlineservice.exception;

import org.springframework.data.crossstore.ChangeSetPersister;

public class CreditLineNotFoundException extends RuntimeException {

    public CreditLineNotFoundException(){
        super("Ha ingresado un id no valido");
    }
}
