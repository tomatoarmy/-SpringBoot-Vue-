package com.fxf.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reserve {
    private int reserveId;
    private String customerName;
    private int customerId;
    private int customerPhone;
    private int roomId;
    private Date reserveInTime;
    private int reserveCondition;
}
