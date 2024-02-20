package com.springboot.TechVenturasNetworkManagementSystemAPI.controller;

import com.springboot.TechVenturasNetworkManagementSystemAPI.dto.PeripheralDeviceDTO;
import com.springboot.TechVenturasNetworkManagementSystemAPI.entity.PeripheralDevice;
import com.springboot.TechVenturasNetworkManagementSystemAPI.service.PeripheralDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/peripheralDevice")
public class PeripheralDeviceController {

    @Autowired
    private PeripheralDeviceService peripheralDeviceService;

    @PostMapping(path = "/createPeripheralDevice")
    public ResponseEntity<PeripheralDevice> createPeripheralDevice(@RequestBody PeripheralDevice peripheralDevice){
        PeripheralDevice createdPeripheralDevice = peripheralDeviceService.createPeripheralDevice(peripheralDevice);
        return new ResponseEntity<>(createdPeripheralDevice, HttpStatus.CREATED);
    }

    @GetMapping(path = "/getAllPeripheralDevice")
    public ResponseEntity<List<PeripheralDevice>> getAllPeripheralDevice(){
        List<PeripheralDevice> peripheralDevices = peripheralDeviceService.getAllPeripheralDevice();
        return new ResponseEntity<>(peripheralDevices, HttpStatus.OK);
    }

    @GetMapping(path = "/getById/{id}")
    public ResponseEntity<PeripheralDevice> getById(@PathVariable long id){
        PeripheralDevice peripheralDevice = peripheralDeviceService.getById(id);
        return new ResponseEntity<>(peripheralDevice, HttpStatus.OK);
    }

    @PutMapping(path = "/updatePeripheralDevice/{id}")
    public ResponseEntity<PeripheralDevice> updatePeripheralDevice(@PathVariable long id, @RequestBody PeripheralDevice peripheralDevice){
        PeripheralDevice updatedDevice = peripheralDeviceService.updatePeripheralDevice(id, peripheralDevice);
        return new ResponseEntity<>(updatedDevice, HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id){
        String deleteMsg = peripheralDeviceService.deleteById(id);
        return new ResponseEntity<>(deleteMsg, HttpStatus.OK);
    }

    @PatchMapping(path = "/partiallyUpdateById/{id}")
    public ResponseEntity<PeripheralDevice> partiallyUpdateById(@PathVariable long id, @RequestBody Map<String, Object> updates){
        PeripheralDevice updated = peripheralDeviceService.partiallyUpdateById(id, updates);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
