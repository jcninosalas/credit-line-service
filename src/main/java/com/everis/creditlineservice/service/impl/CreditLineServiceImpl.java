package com.everis.creditlineservice.service.impl;

import com.everis.creditlineservice.model.CreditLine;
import com.everis.creditlineservice.model.CreditLineResponse;
import com.everis.creditlineservice.repository.CreditLineRepository;
import com.everis.creditlineservice.service.CreditLineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@Slf4j
public class CreditLineServiceImpl implements CreditLineService {

    @Autowired
    private CreditLineRepository repository;

    @Override
    public Mono<CreditLineResponse> create(CreditLine creditLine) {
        return repository.insert(creditLine)
                .flatMap(this::newCreditLineResponse);
    }

    @Override
    public Flux<CreditLine> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<CreditLine> findById(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<CreditLineResponse> update(CreditLine creditLine) {
        return repository.save(creditLine)
                .flatMap(this::updateCreditLineResponse);
    }

    @Override
    public Mono<String> delete(String id) {
        return repository.findById(id)
                .flatMap( creditLine -> {
                    creditLine.setActive(false);
                    repository.save(creditLine).subscribe();
                    return Mono.just("Linea de credito cancelada");
                })
                .defaultIfEmpty("Registro no encontrado");
    }

    private Mono<CreditLineResponse> newCreditLineResponse(CreditLine newCreditLine) {
       log.info("Created credit line : {} ", newCreditLine.toString());
       return Mono.just( new CreditLineResponse(
                "Se abrió la linea de credito con exito",
                HttpStatus.OK,
                Map.of("creditLine", newCreditLine)
        ));
    }

    private Mono<CreditLineResponse> updateCreditLineResponse(CreditLine creditLine) {
        log.info("Updated credit line : {} ", creditLine.toString());
        return Mono.just( new CreditLineResponse(
                "Se actualizó la linea de credito con exito",
                HttpStatus.OK,
                Map.of("creditLine", creditLine)
        ));
    }
}
