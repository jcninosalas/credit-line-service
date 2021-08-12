package com.everis.creditlineservice.controller;

import com.everis.creditlineservice.model.CreditLine;
import com.everis.creditlineservice.model.CreditLineResponse;
import com.everis.creditlineservice.service.CreditLineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/credit-lines")
@Slf4j
public class CreditLineController {

    @Autowired
    private CreditLineService service;

    @PostMapping
    public Mono<CreditLineResponse> newCreditLine(
            @Valid
            @RequestBody CreditLine creditLine) {
        return service.create(creditLine);
    }

    @GetMapping
    public Flux<CreditLine> getAll(){
        return service.findAll();
    }

    @GetMapping("/credit-line")
    public Mono<CreditLine> findCreditLineById(@RequestParam String id) { return  service.findById(id); }

    @PutMapping
    public Mono<CreditLineResponse> updateCreditLine(
            @Valid
            @RequestBody CreditLine creditLine,
            @RequestParam String id) {
        return service.update(creditLine, id);
    }

    @DeleteMapping
    public Mono<String> disableCreditLine(String id) {
        return service.delete(id);
    }
}
