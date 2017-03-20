package com.example.pellesam.outerspacemanager.Entity;

import java.io.Serializable;
import java.security.Timestamp;

/**
 * Created by mac14 on 20/03/2017.
 */

public class Attack implements Serializable{

    private long time;

    private String fleetSend;

    public Attack(long time, String fleetSend) {
        this.time = time;
        this.fleetSend = fleetSend;
    }

    public Attack() {
    }

    public long getTime() {
        return time;
    }

    public String getFleetSend() {
        return fleetSend;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setFleetSend(String fleetSend) {
        this.fleetSend = fleetSend;
    }
}
