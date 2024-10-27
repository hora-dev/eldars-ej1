package com.eldars;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AmexServiceFee implements ServiceFeeStrategy {
    @Override
    public BigDecimal calculateFee(LocalDate currentDate) {
        int month = currentDate.getMonthValue();
        return BigDecimal.valueOf(month * 0.1);
    }
}
