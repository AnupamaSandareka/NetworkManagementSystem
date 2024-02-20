package com.springboot.TechVenturasNetworkManagementSystemAPI.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gateway {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serialNumber", unique = true, nullable = false)
    private String serialNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "ipAddress", nullable = false)
    @Pattern(regexp = "^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$")
    private String ipAddress;

    @OneToMany(mappedBy = "gateway", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PeripheralDevice> peripheralDevices = new ArrayList<>();
}
