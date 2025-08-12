package com.deliverytech.delivery_api.dto;

import lombok.Data;

@Data
public class RestaurantDto {
    
    private String name;
    private String description;
    private String address;
        
 
    public RestaurantDto(String name, String description, String address) {
        this.name = name;
        this.description = description;
        this.address = address;
    }
    public RestaurantDto() {
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
