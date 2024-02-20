package com.springboot.TechVenturasNetworkManagementSystemAPI.repository;

import com.springboot.TechVenturasNetworkManagementSystemAPI.entity.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface GatewayRepo extends JpaRepository<Gateway, Long> {
    boolean existsBySerialNumber(String serialNumber);
}
