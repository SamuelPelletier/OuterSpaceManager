package com.example.pellesam.outerspacemanager.Entity;

import java.io.Serializable;

/**
 * Created by mac14 on 14/03/2017.
 */

public class Amount implements Serializable {

    private Integer amount;

    public Amount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

}
