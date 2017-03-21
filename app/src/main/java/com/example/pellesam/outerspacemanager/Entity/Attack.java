package com.example.pellesam.outerspacemanager.Entity;

import java.io.Serializable;
import java.security.Timestamp;

/**
 * Created by mac14 on 20/03/2017.
 */

public class Attack implements Serializable{

    private long begin;

    private long end;

    private String username;

    private String fleetSend;

    public Attack(long begin,long end,String username, String fleetSend) {
        this.begin = begin;
        this.end = end;
        this.username = username;
        this.fleetSend = fleetSend;
    }

    public Attack() {
    }

    public long getBegin() {
        return begin;
    }

    public long getEnd() {
        return end;
    }

    public String getFleetSend() {
        return fleetSend;
    }

    public String getUsername() {
        return username;
    }

    public void setBegin(long begin) {
        this.begin = begin;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public void setFleetSend(String fleetSend) {
        this.fleetSend = fleetSend;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
