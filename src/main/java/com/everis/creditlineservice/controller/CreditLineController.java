package com.everis.creditlineservice.controller;

import com.everis.creditlineservice.model.CreditLine;
import com.everis.creditlineservice.model.CreditLineResponse;
import com.everis.creditlineservice.service.CreditLineService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/credit-lines")
public class CreditLineController {
    @Autowired
    private CreditLineService service;

    @PostMapping
    public Mono<CreditLineResponse> newCreditLine(@Valid @RequestBody CreditLine creditLine) {
        return service.create(creditLine);
    }

    @GetMapping
    public Flux<CreditLine> getAll(@RequestParam String customerDocument) {
        return service.findAll(customerDocument);
    }

    @GetMapping("/credit-line")
    public Mono<CreditLine> findCreditLineById(@RequestParam String id) {
        return  service.findById(id);
    }

    @PutMapping("/credit-line")
    public Mono<CreditLineResponse> updateCreditLine(@Valid @RequestBody CreditLine creditLine,
                                                     @RequestParam String id) {
        return service.update(creditLine, id);
    }
}
