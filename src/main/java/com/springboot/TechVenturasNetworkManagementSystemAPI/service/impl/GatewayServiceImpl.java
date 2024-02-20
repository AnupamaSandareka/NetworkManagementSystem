package com.springboot.TechVenturasNetworkManagementSystemAPI.service.impl;

import com.springboot.TechVenturasNetworkManagementSystemAPI.entity.Gateway;
import com.springboot.TechVenturasNetworkManagementSystemAPI.repository.GatewayRepo;
import com.springboot.TechVenturasNetworkManagementSystemAPI.service.GatewayService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class GatewayServiceImpl implements GatewayService {

    @Autowired
    private GatewayRepo gatewayRepo;

    @Autowired
    private Validator validator;

    @Override
    public Gateway createGateway(Gateway gateway) {

        //validate gateway
        validateGateway(gateway);

        boolean checkGateway = gatewayRepo.existsBySerialNumber(gateway.getSerialNumber());

        if(!checkGateway){
            return gatewayRepo.save(gateway);
        }else {
            throw new RuntimeException("Gateway with serial number " + gateway.getSerialNumber() + " already exists");
        }
    }

    @Override
    public List<Gateway> getAllGateways() {
        List<Gateway> gateways = gatewayRepo.findAll();
        return gateways;
    }

    @Override
    public Gateway getGatewayById(long id) {
        Optional<Gateway> gatewayOptional = gatewayRepo.findById(id);
        if (gatewayOptional.isPresent()) {
            return gatewayOptional.get();
        } else {
            throw new RuntimeException("Gateway with id " + id + " not found");
        }
    }

    @Override
    public Gateway updateGateway(long id, Gateway gateway) {

        validateGateway(gateway);

        Optional<Gateway> checkGateway = gatewayRepo.findById(id);

        if(checkGateway.isPresent()){
            Gateway existGateway = checkGateway.get();
            existGateway.setSerialNumber(gateway.getSerialNumber());
            existGateway.setName(gateway.getName());
            existGateway.setIpAddress(gateway.getIpAddress());

            return gatewayRepo.save(existGateway);
        }else {
            throw new RuntimeException("Gateway with id " + id + " not found");
        }
    }

    @Override
    public String deleteGatewayById(long id) {
        Optional<Gateway> gateway = gatewayRepo.findById(id);

        if(gateway.isPresent()){
            gatewayRepo.deleteById(id);
            return "Gateway with id " + id + " Deleted Successfully";
        }else {
            return "Gateway with id " + id + " not found";
        }
    }

    @Override
    public Gateway partiallyUpdateGateway(long id, Map<String, Object> updates) {

        Optional<Gateway> gateway = gatewayRepo.findById(id);

        if(gateway.isPresent()){
            Gateway existGateway = gateway.get();

            if(updates.containsKey("serialNumber")){
                existGateway.setSerialNumber((String) updates.get("serialNumber"));
            }
            if(updates.containsKey("name")){
                existGateway.setSerialNumber((String) updates.get("name"));
            }
            if(updates.containsKey("ipAddress")){
                existGateway.setSerialNumber((String) updates.get("ipAddress"));
            }

            validateGateway(existGateway);
            return gatewayRepo.save(existGateway);
        }
        return null;
    }

    public void validateGateway(Gateway gateway) {
        Set<ConstraintViolation<Gateway>> violations = validator.validate(gateway);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Gateway> violation : violations) {
                sb.append(violation.getMessage()).append("\n");
            }
            throw new RuntimeException("Validation failed: " + sb.toString());
        }
    }
}
