package com.eldars;

import java.math.BigDecimal;
import java.time.LocalDate;

class NaraServiceFee implements ServiceFeeStrategy{
    @Override
    public BigDecimal calculateFee(LocalDate currentDate) {
        int day = currentDate.getDayOfMonth();
        return BigDecimal.valueOf(day * 0.5);
    }
}
