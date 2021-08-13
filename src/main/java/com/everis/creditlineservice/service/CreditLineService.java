package com.everis.creditlineservice.service;

import com.everis.creditlineservice.model.CreditLine;
import com.everis.creditlineservice.model.CreditLineResponse;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditLineService {

    Mono<CreditLineResponse> create (CreditLine creditLine);

    Flux<CreditLine> findAll (String customerDocument);

    Mono<CreditLine> findById (String id);

    Mono<CreditLineResponse> update(CreditLine creditLine, String id);

}
