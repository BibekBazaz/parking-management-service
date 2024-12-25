package com.bibebazaz.parking_management_service.api.model;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class BookSlotRequest {

    @NonNull
    private String vehicleType;
    @NonNull
    private String vehicleNumber;

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
