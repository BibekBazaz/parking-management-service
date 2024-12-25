package com.bibebazaz.parking_management_service.dao;

import com.bibebazaz.parking_management_service.model.entities.ParkingSlot;
import org.springframework.data.repository.CrudRepository;

public interface ParkingSlotRepository extends CrudRepository<ParkingSlot,Long> {
}
