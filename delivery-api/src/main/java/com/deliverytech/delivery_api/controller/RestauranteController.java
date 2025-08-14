package com.deliverytech.delivery_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery_api.dto.RestauranteDto;
import com.deliverytech.delivery_api.service.RestauranteService;
@CrossOrigin(origins = "*")

@RequestMapping("/api/restaurantes")

@RestController
public class RestauranteController {
    private RestauranteService restauranteService;
 
    @GetMapping("/findAll")
    public List<RestauranteDto> listRestaurantes() {
        return restauranteService.findAll();
    }
}
