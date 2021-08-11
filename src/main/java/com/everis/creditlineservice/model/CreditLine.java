package com.everis.creditlineservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Document
@ToString
public class CreditLine {

    @Id
    private String id;
    private BigDecimal creditLimit;
    private BigDecimal availableCredit;
    private String customerDocument;
    private Date createdAt;
    private Date modifiedAt;
    private boolean isActive;
}
