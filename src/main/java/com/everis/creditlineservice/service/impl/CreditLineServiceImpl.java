package com.everis.creditlineservice.service.impl;

import com.everis.creditlineservice.exception.CreditLineNotFoundException;
import com.everis.creditlineservice.model.CreditLine;
import com.everis.creditlineservice.model.CreditLineResponse;
import com.everis.creditlineservice.repository.CreditLineRepository;
import com.everis.creditlineservice.service.CreditLineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

@Service
@Slf4j
public class CreditLineServiceImpl implements CreditLineService {

    @Autowired
    private CreditLineRepository repository;

    @Override
    public Mono<CreditLineResponse> create(CreditLine creditLine) {
       return repository.save(openCreditLine.apply(creditLine))
               .flatMap(creditLineResponse);
    }

    @Override
    public Flux<CreditLine> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<CreditLine> findById(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new CreditLineNotFoundException()));
    }

    @Override
    public Mono<CreditLineResponse> update(CreditLine creditLine, String id) {
        return repository.findById(id)
                .map( c -> updateCreditLine.apply(c, creditLine))
                .flatMap(repository::save)
                .flatMap(creditLineResponse)
                .switchIfEmpty(Mono.error(new CreditLineNotFoundException()));
    }


    private final Function<CreditLine, CreditLine> openCreditLine = creditLine -> {
        creditLine.setActive(true);
        creditLine.setCreatedAt(Date.from(Instant.now()));
        return creditLine;
    };

    private final Function<CreditLine, Mono<CreditLineResponse>> creditLineResponse =  creditLine ->
            Mono.just(new CreditLineResponse(
                "Operacion realizada con exito",
                HttpStatus.OK,
                Map.of("creditLine", creditLine)
    ));

    private final BiFunction<CreditLine, CreditLine, CreditLine> updateCreditLine =
            (creditLine, creditLineToUpdate) -> {
                log.info("para actualizar : {}", creditLineToUpdate);
                creditLineToUpdate.setId(creditLine.getId());
                creditLineToUpdate.setModifiedAt(Date.from(Instant.now()));
                creditLineToUpdate.setCreatedAt(creditLine.getCreatedAt());
                creditLineToUpdate.setAvailableCredit(
                        creditLineToUpdate.getAvailableCredit() == null ?
                                creditLine.getAvailableCredit() : creditLineToUpdate.getAvailableCredit()
                );
                creditLineToUpdate.setActive(true);
                log.info("actualizada : {}", creditLineToUpdate);
                return creditLineToUpdate;
            };
}
