package com.everis.creditlineservice.repository;

import com.everis.creditlineservice.model.CreditLine;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CreditLineRepository extends ReactiveMongoRepository<CreditLine, String> {

    Flux<CreditLine> findAllByCustomerDocument (String customerDocument);
}
