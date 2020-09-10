package com.bolly.batch.ch02;

import org.springframework.batch.item.ItemProcessor;

public class CreditBillProcessor implements ItemProcessor<CreditBill,CreditBill> {
    @Override
    public CreditBill process(CreditBill item) throws Exception {
        return item;
    }
}
