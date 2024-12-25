package com.bibebazaz.parking_management_service.services;

import com.bibebazaz.parking_management_service.api.exceptions.ParkingException;
import com.bibebazaz.parking_management_service.dao.ParkingSlotRepository;
import com.bibebazaz.parking_management_service.model.entities.ParkingSlot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class ParkingServiceImpl implements ParkingService {
    private static final Logger logger = LoggerFactory.getLogger(ParkingServiceImpl.class);

    @Autowired
    private final ParkingSlotRepository repository;

    public ParkingServiceImpl(ParkingSlotRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ParkingSlot> checkAllSlots() {
        logger.info("Fetching all parking slots...");
        return (List<ParkingSlot>) repository.findAll();
    }

    @Override
    public List<ParkingSlot> checkSlotsByVehicleType(String vehicleType) {
        logger.info("Checking slots for vehicle type: {}", vehicleType);
        List<ParkingSlot> slots = StreamSupport.stream(repository.findAll().spliterator(), false)
                .filter(slot -> vehicleType.equals(slot.getVehicleType()) && !slot.isOccupied())
                .toList();

        if (slots.isEmpty()) {
            throw new ParkingException("No available slots for vehicle type: " + vehicleType);
        }
        return slots;
    }

    @Override
    public void bookSlot(Long slotId, String vehicleType) {
        logger.info("Booking slot with ID: {} for vehicle type: {}", slotId, vehicleType);
        if(repository.findById(slotId).isPresent()){
            ParkingSlot slot = repository.findById(slotId).get();
            if (slot.isOccupied()) {
                throw new ParkingException("Slot with ID " + slotId + " is already occupied");
            }
            slot.setOccupied(true);
            slot.setVehicleType(vehicleType);
            repository.save(slot);
        }
    }

    @Override
    public void freeSlot(Long slotId) {
        logger.info("Freeing slot with ID: {}", slotId);
        ParkingSlot slot = repository.findById(slotId).orElseThrow(() -> new RuntimeException("Slot not found"));

        if (!slot.isOccupied()) {
            throw new ParkingException("Slot with ID " + slotId + " is already free");
        }

        slot.setOccupied(false);
        slot.setVehicleType(null);
        repository.save(slot);
    }

    @Override
    public List<ParkingSlot> getSlotsByLevel(Long levelId) {
        logger.info("Fetching all slots for level ID: {}", levelId);
        List<ParkingSlot> slots = StreamSupport.stream(repository.findAll().spliterator(), false).toList();
        if (slots.isEmpty()) {
            throw new ParkingException("No slots found for level ID " + levelId);
        }
        return  slots;
    }

    @Override
    public void loadParkingSlots(List<ParkingSlot> parkingSlots) {
        repository.saveAll(parkingSlots);
        logger.info("Data Load successful");

    }
}
