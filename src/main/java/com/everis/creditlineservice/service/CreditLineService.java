package com.everis.creditlineservice.service;

import com.everis.creditlineservice.model.CreditLine;
import com.everis.creditlineservice.model.CreditLineResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditLineService {

    Mono<CreditLineResponse> create (CreditLine creditLine);

    Flux<CreditLine> findAll ();

    Mono<CreditLine> findById (String id);

    Mono<CreditLineResponse> update(CreditLine creditLine);

    public Mono<String> delete (String id);

}
