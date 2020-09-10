package com.bolly.batch.ch02;

import lombok.Data;

@Data
public class CreditBill {
    private String accountId;
    private String name;
    private double amount = 0;
    private String date;
    private String address;
}
