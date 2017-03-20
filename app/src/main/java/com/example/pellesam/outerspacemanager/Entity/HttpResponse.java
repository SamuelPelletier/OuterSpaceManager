package com.example.pellesam.outerspacemanager.Entity;

import java.io.Serializable;

/**
 * Created by mac14 on 14/03/2017.
 */

public class HttpResponse implements Serializable {

    private String code;

    private long attackTime;

    public HttpResponse(String code, long attackTime) {
        this.code = code;
        this.attackTime = attackTime;
    }

    public String getCode() {
        return code;
    }

    public long getAttackTime() {
        return attackTime;
    }
}
