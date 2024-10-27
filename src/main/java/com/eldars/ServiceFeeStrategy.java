package com.eldars;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ServiceFeeStrategy {
    BigDecimal calculateFee(LocalDate currentDate);
}
