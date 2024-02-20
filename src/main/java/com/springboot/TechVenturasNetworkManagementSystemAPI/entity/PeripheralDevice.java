package com.springboot.TechVenturasNetworkManagementSystemAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeripheralDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vendor", nullable = false)
    private String vendor;

    @Column(name = "dateCreated", nullable = false)
    private Date dateCreated;

    @Column(name = "statusOnline", nullable = false)
    private Boolean statusOnline;

    @JsonIgnoreProperties({"peripheralDevices", "hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gateway_id", nullable = false)
    private Gateway gateway;
}
