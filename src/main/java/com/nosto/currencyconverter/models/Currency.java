package com.nosto.currencyconverter.models;

import com.nosto.currencyconverter.enums.CurrencySymbol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Currency {
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    CurrencySymbol currencySymbol;
    String currencyName;
}
