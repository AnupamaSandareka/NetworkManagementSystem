package com.springboot.TechVenturasNetworkManagementSystemAPI.service;

import com.springboot.TechVenturasNetworkManagementSystemAPI.dto.PeripheralDeviceDTO;
import com.springboot.TechVenturasNetworkManagementSystemAPI.entity.PeripheralDevice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PeripheralDeviceService {
    PeripheralDevice createPeripheralDevice(PeripheralDevice peripheralDevice);

    List<PeripheralDevice> getAllPeripheralDevice();

    PeripheralDevice getById(long id);

    PeripheralDevice updatePeripheralDevice(long id, PeripheralDevice peripheralDevice);

    String deleteById(long id);

    PeripheralDevice partiallyUpdateById(long id, Map<String, Object> updates);
}
