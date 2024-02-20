package com.springboot.TechVenturasNetworkManagementSystemAPI.repository;

import com.springboot.TechVenturasNetworkManagementSystemAPI.entity.PeripheralDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface PeripheralDeviceRepo extends JpaRepository<PeripheralDevice, Long> {
    int countByGatewayId(long gatewayId);
}
