package com.example.hrs.entities;

public class ShipMethod {


    private Integer shipMethodId;
    private String shipMethodName;


    public Integer getShipMethodId() {
        return shipMethodId;
    }

    public void setShipMethodId(Integer shipMethodId) {
        this.shipMethodId = shipMethodId;
    }

    public String getShipMethodName() {
        return shipMethodName;
    }

    public void setShipMethodName(String shipMethodName) {
        this.shipMethodName = shipMethodName;
    }

    public ShipMethod() {
    }

    public ShipMethod(Integer shipMethodId, String shipMethodName) {
        this.shipMethodId = shipMethodId;
        this.shipMethodName = shipMethodName;
    }

    @Override
    public String toString() {
        return  shipMethodName ;
    }
}
