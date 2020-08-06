package com.prorocketeers.bscpackagesjanstastny.repository;

import com.prorocketeers.bscpackagesjanstastny.domain.Fee;

import java.util.List;

public interface IFeeRepo {
    List<Fee> getFees();

    void createFee(Fee fee);
}
