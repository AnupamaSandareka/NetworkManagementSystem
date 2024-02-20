package com.springboot.TechVenturasNetworkManagementSystemAPI.service;

import com.springboot.TechVenturasNetworkManagementSystemAPI.entity.Gateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public interface GatewayService {
    Gateway createGateway(Gateway gateway);

    List<Gateway> getAllGateways();

    Gateway getGatewayById(long id);

    Gateway updateGateway(long id, Gateway gateway);

    String deleteGatewayById(long id);

    Gateway partiallyUpdateGateway(long id, Map<String, Object> updates);
}
