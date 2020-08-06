package com.prorocketeers.bscpackagesjanstastny.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class PackagePost {
    private double weight;
    private String postCode;
    private BigDecimal fee;

    public PackagePost(double weight, String postCode, BigDecimal fee) {
        this.weight = weight;
        this.postCode = postCode;
        this.fee = fee;
    }
}
