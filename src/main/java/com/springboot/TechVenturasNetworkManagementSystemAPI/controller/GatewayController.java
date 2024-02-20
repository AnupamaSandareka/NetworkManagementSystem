package com.springboot.TechVenturasNetworkManagementSystemAPI.controller;

import com.springboot.TechVenturasNetworkManagementSystemAPI.entity.Gateway;
import com.springboot.TechVenturasNetworkManagementSystemAPI.service.GatewayService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/gateway")
public class GatewayController {

    @Autowired
    private GatewayService gatewayService;

    @PostMapping(path = "/createGateway")
    public ResponseEntity<Gateway> createGateway(@Valid @RequestBody Gateway gateway){
       Gateway createGateway = gatewayService.createGateway(gateway);
       return new ResponseEntity<>(createGateway, HttpStatus.CREATED);
    }

    @GetMapping(path = "/getAllGateways")
    public ResponseEntity<List<Gateway>> getAllGateways(){
        List<Gateway> gateways = gatewayService.getAllGateways();
        return new ResponseEntity<>(gateways, HttpStatus.OK);
    }

    @GetMapping(path = "/getGatewayById/{id}")
    public ResponseEntity<Gateway> getGatewayById(@PathVariable long id){
        Gateway gateway = gatewayService.getGatewayById(id);
        return new ResponseEntity<>(gateway, HttpStatus.OK);
    }

    @PutMapping(path = "/updateGateway/{id}")
    public ResponseEntity<Gateway> updateGateway(@PathVariable long id, @Valid @RequestBody Gateway gateway){
        Gateway updatedGateway = gatewayService.updateGateway(id,gateway);
        return new ResponseEntity<>(updatedGateway, HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteGatewayById/{id}")
    public ResponseEntity<String> deleteGatewayById(@PathVariable long id){
        String deleteMsg = gatewayService.deleteGatewayById(id);
        return new ResponseEntity<>(deleteMsg, HttpStatus.OK);
    }

    @PatchMapping("/partiallyUpdateGateway/{id}")
    public ResponseEntity<Gateway> partiallyUpdateGateway(@PathVariable long id, @RequestBody Map<String, Object> updates){
        Gateway partiallyUpdatedGateway = gatewayService.partiallyUpdateGateway(id, updates);
        return new ResponseEntity<>(partiallyUpdatedGateway, HttpStatus.OK);
    }
}
