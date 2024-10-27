package com.eldars;

import java.math.BigDecimal;
import java.time.LocalDate;

class VisaServiceFee implements ServiceFeeStrategy {
    @Override
    public BigDecimal calculateFee(LocalDate currentDate) {
        int year = currentDate.getYear() % 100;
        int month = currentDate.getMonthValue();
        return BigDecimal.valueOf(year).divide(BigDecimal.valueOf(month), 2, BigDecimal.ROUND_HALF_UP);
    }
}
