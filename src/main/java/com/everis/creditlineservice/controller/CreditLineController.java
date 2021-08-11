package com.everis.creditlineservice.controller;

import com.everis.creditlineservice.model.CreditLine;
import com.everis.creditlineservice.model.CreditLineResponse;
import com.everis.creditlineservice.service.CreditLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/credit-line")
public class CreditLineController {

    @Autowired
    private CreditLineService service;

    @PostMapping("/new")
    public Mono<CreditLineResponse> newCreditLine(CreditLine creditLine) {
        return service.create(creditLine);
    }

    @GetMapping("/get-all")
    public Flux<CreditLine> getAll(){
        return service.findAll();
    }

    @PutMapping("/update")
    public Mono<CreditLineResponse> updateCreditLine(CreditLine creditLine) {
        return service.update(creditLine);
    }

    @DeleteMapping("/disable")
    public Mono<String> disableCreditLine(String id) {
        return service.delete(id);
    }
}
