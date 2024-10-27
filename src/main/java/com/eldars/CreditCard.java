package com.eldars;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
class CreditCard {
    private String brand;
    private String cardNumber;
    private LocalDate expirationDate;
    private String cardFullName;

    @Override
    public String toString() {
        return "CreditCard{" +
                "brand='" + brand + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", expirationDate=" + expirationDate +
                ", cardFullName='" + cardFullName + '\'' +
                '}';
    }
}