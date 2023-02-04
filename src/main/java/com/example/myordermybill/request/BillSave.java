package com.example.myordermybill.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BillSave {
    private Long companyId;
    private Long userId;
    private BigDecimal total;
}
