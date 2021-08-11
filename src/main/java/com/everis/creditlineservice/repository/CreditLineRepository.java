package com.everis.creditlineservice.repository;

import com.everis.creditlineservice.model.CreditLine;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CreditLineRepository extends ReactiveMongoRepository<CreditLine, String> {
}
