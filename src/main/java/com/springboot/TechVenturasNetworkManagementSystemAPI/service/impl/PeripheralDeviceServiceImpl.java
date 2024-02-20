package com.springboot.TechVenturasNetworkManagementSystemAPI.service.impl;

import com.springboot.TechVenturasNetworkManagementSystemAPI.entity.Gateway;
import com.springboot.TechVenturasNetworkManagementSystemAPI.entity.PeripheralDevice;
import com.springboot.TechVenturasNetworkManagementSystemAPI.repository.PeripheralDeviceRepo;
import com.springboot.TechVenturasNetworkManagementSystemAPI.service.PeripheralDeviceService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.ref.PhantomReference;
import java.util.*;

@Service
public class PeripheralDeviceServiceImpl implements PeripheralDeviceService {

    @Autowired
    private PeripheralDeviceRepo peripheralDeviceRepo;

    @Autowired
    private Validator validator;

    @Override
    public PeripheralDevice createPeripheralDevice(PeripheralDevice peripheralDevice) {

        validatePeripheralDevice(peripheralDevice);

        long gatewayId = peripheralDevice.getGateway().getId();
        int count = peripheralDeviceRepo.countByGatewayId(gatewayId);

        if (count >= 10) {
            throw new RuntimeException("Cannot add more than 10 peripheral devices to a gateway");
        }

        return peripheralDeviceRepo.save(peripheralDevice);
    }

    @Override
    public List<PeripheralDevice> getAllPeripheralDevice() {
        return peripheralDeviceRepo.findAll();
    }

    @Override
    public PeripheralDevice getById(long id){
        Optional<PeripheralDevice> checkDevice = peripheralDeviceRepo.findById(id);

        if(checkDevice.isPresent()){
            return checkDevice.get();
        }else{
            throw new RuntimeException("Gateway with id " + id + " not found");
        }
    }

    @Override
    public PeripheralDevice updatePeripheralDevice(long id, PeripheralDevice peripheralDevice) {
        Optional<PeripheralDevice> checkDevice = peripheralDeviceRepo.findById(id);

        if(checkDevice.isPresent()){
            PeripheralDevice existDevice = checkDevice.get();
            existDevice.setVendor(peripheralDevice.getVendor());
            existDevice.setDateCreated(peripheralDevice.getDateCreated());
            existDevice.setStatusOnline(peripheralDevice.getStatusOnline());
            existDevice.setGateway(peripheralDevice.getGateway());

            long gatewayId = peripheralDevice.getGateway().getId();
            int count = peripheralDeviceRepo.countByGatewayId(gatewayId);

            if (count >= 10) {
                throw new RuntimeException("Cannot add more than 10 peripheral devices to a gateway");
            }

            return peripheralDeviceRepo.save(existDevice);
        }else{
            throw new RuntimeException("Gateway with id " + id + " not found");
        }
    }

    @Override
    public String deleteById(long id) {
        Optional<PeripheralDevice> checkDevice = peripheralDeviceRepo.findById(id);

        if(checkDevice.isPresent()){
            peripheralDeviceRepo.deleteById(id);
            return "Gateway with id " + id + " deleted successfully";
        }else{
            throw new RuntimeException("Gateway with id " + id + " not found");
        }
    }

    @Override
    public PeripheralDevice partiallyUpdateById(long id, Map<String, Object> updates) {
        Optional<PeripheralDevice> check = peripheralDeviceRepo.findById(id);

        if (check.isPresent()) {
            PeripheralDevice existDevice = check.get();

            if (updates.containsKey("vendor")) {
                existDevice.setVendor((String) updates.get("vendor"));
            }
            if (updates.containsKey("dateCreated")) {
                existDevice.setDateCreated((Date) updates.get("dateCreated"));
            }
            if (updates.containsKey("statusOnline")) {
                existDevice.setStatusOnline((Boolean) updates.get("statusOnline"));
            }
            if (updates.containsKey("gateway")) {
                long gatewayId = existDevice.getGateway().getId();
                int count = peripheralDeviceRepo.countByGatewayId(gatewayId);

                if (count >= 10) {
                    throw new RuntimeException("Cannot add more than 10 peripheral devices to a gateway");
                }
                existDevice.setGateway((Gateway) updates.get("gateway"));
            }

            validatePeripheralDevice(existDevice);
            return peripheralDeviceRepo.save(existDevice);
        } else {
            throw new RuntimeException("Peripheral device with id " + id + " not found");
        }
    }


    private void validatePeripheralDevice(PeripheralDevice peripheralDevice) {
        Set<ConstraintViolation<PeripheralDevice>> violations = validator.validate(peripheralDevice);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<PeripheralDevice> violation : violations) {
                sb.append(violation.getMessage()).append("\n");
            }
            throw new RuntimeException("Validation failed: " + sb.toString());
        }
    }
}
