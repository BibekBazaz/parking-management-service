package com.bibebazaz.parking_management_service.api.model;

import org.springframework.stereotype.Component;

@Component
public class BookSlotResponse {

    private String vehicleType;
    private long slot;
    private String vehicleNumber;

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public long getSlot() {
        return slot;
    }

    public void setSlot(long slot) {
        this.slot = slot;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
