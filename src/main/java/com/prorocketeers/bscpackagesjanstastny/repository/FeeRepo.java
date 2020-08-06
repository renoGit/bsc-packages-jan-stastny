package com.prorocketeers.bscpackagesjanstastny.repository;

import com.prorocketeers.bscpackagesjanstastny.domain.Fee;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class FeeRepo implements IFeeRepo {

    List<Fee> fees = new ArrayList<>();

    @Override
    public List<Fee> getFees() {
        return fees;
    }

    @Override
    public void createFee(Fee fee) {
        fees.add(fee);
    }

}
