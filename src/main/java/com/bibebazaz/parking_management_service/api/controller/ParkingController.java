package com.bibebazaz.parking_management_service.api.controller;

import com.bibebazaz.parking_management_service.api.model.BookSlotRequest;
import com.bibebazaz.parking_management_service.api.model.BookSlotResponse;
import com.bibebazaz.parking_management_service.model.entities.ParkingSlot;
import com.bibebazaz.parking_management_service.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    @Autowired
    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }
    @GetMapping(value ="/slots", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ParkingSlot>> getAllSlots() {
        List<ParkingSlot> slots = parkingService.checkAllSlots();
        return ResponseEntity.ok(slots);
    }

    @GetMapping(value="/slots/{vehicleType}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ParkingSlot>> getSlotsByVehicleType(@PathVariable String vehicleType) {
        List<ParkingSlot> slots = parkingService.checkSlotsByVehicleType(vehicleType);
        return ResponseEntity.ok(slots);
    }

    @PostMapping(value="/book/{slotId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookSlotResponse> bookSlot(@PathVariable Long slotId, @RequestBody BookSlotRequest bookSlotRequest) {
        parkingService.bookSlot(slotId, bookSlotRequest.getVehicleType());
        BookSlotResponse bookSlotResponse = new BookSlotResponse();
        bookSlotResponse.setSlot(slotId);
        bookSlotResponse.setVehicleNumber(bookSlotRequest.getVehicleNumber());
        bookSlotResponse.setVehicleType(bookSlotRequest.getVehicleType());
        return  ResponseEntity.ok(bookSlotResponse);
    }

    @PostMapping(value = "/free/{slotId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> freeSlot(@PathVariable Long slotId) {
        parkingService.freeSlot(slotId);
        return ResponseEntity.ok("Slot " + slotId + " successfully freed");
    }

    @PostMapping(value = "/load-slots", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> loadParkingSlots(@RequestBody List<ParkingSlot> parkingSlots) {
        parkingService.loadParkingSlots(parkingSlots);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"message\": \"Parking slots loaded successfully.\"}");
    }
}


