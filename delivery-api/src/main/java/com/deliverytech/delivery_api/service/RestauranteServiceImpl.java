package com.deliverytech.delivery_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverytech.delivery_api.dto.RestauranteDto;
import com.deliverytech.delivery_api.entity.Restaurante;
import com.deliverytech.delivery_api.repository.RestauranteRepository;

@Service
public class RestauranteServiceImpl {
    @Autowired
    private RestauranteRepository repository;

    public RestauranteServiceImpl(RestauranteRepository restaurantRepository) {
        this.repository = restaurantRepository;
    }

    public RestauranteServiceImpl() {
        super();
    }

    public List<RestauranteDto> findAll() {
        return repository.findAll().stream().map(this::ConvertEntityToDto).collect(Collectors.toList());
    }

    private RestauranteDto ConvertEntityToDto(Restaurante entity) {
        RestauranteDto dto = new RestauranteDto();
        dto.setName(entity.getNome());
        dto.setDescription((entity.getDescricao()));
        dto.setAddress(entity.getEndereco());
        return dto;
    }

}
