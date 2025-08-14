package com.deliverytech.delivery_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.deliverytech.delivery_api.dto.RestauranteDto;

@Service
public interface RestauranteService {
    public List<RestauranteDto> findAll();

}
