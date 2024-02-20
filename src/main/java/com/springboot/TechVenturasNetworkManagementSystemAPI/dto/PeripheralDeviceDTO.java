package com.springboot.TechVenturasNetworkManagementSystemAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeripheralDeviceDTO {
    private Long id;
    private String vendor;
    private Date dateCreated;
    private Boolean statusOnline;
    private Long gatewayId;
}
