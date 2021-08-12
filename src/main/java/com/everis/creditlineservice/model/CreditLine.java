package com.everis.creditlineservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Document
@ToString
public class CreditLine {

    @Id
    private String id;

    @NotNull(message = "El limite de credito debe ser mayor o igual a 0")
    private BigDecimal creditLimit;

    private BigDecimal availableCredit;

    @NotNull
    @Size(min = 8, message = "El numero de documento requiere minimo 8 digitos")
    @Pattern(regexp = "^[0-9]*$", message = "El numero de documento solo acepta numeros")
    private String customerDocument;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createdAt;

    private Date modifiedAt;

    private boolean isActive;
}
