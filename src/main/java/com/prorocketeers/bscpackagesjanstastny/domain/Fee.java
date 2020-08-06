package com.prorocketeers.bscpackagesjanstastny.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
public class Fee {
    /** weight of package */
    private Double weight;
    /** fee for delivery */
    private BigDecimal fee;

    public Fee(Double weight, BigDecimal fee) {
        this.weight = weight;
        this.fee = fee;
    }
}
