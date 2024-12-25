package com.bibebazaz.parking_management_service.services;

import com.bibebazaz.parking_management_service.model.entities.ParkingSlot;

import java.util.List;

public interface ParkingService {

    List<ParkingSlot> checkAllSlots();
    List<ParkingSlot> checkSlotsByVehicleType(String vehicleType);
    void bookSlot(Long slotId, String vehicleType);
    void freeSlot(Long slotId);
    List<ParkingSlot> getSlotsByLevel(Long levelId);
    void loadParkingSlots(List<ParkingSlot> parkingSlots);
}

