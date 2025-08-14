package com.deliverytech.delivery_api.dto;

import lombok.Data;

@Data
public class RestauranteDto {
    
    private String name;
    private String description;
    private String address;
        
 
    public RestauranteDto(String name, String description, String address) {
        this.name = name;
        this.description = description;
        this.address = address;
    }
    public RestauranteDto() {
        super();
    }   
   
    @Override
    public String toString() {
        return "RestaurantDto{" +
               "name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", address='" + address + '\'' +
               '}';
    }

}
