package com.gdeal.brewmaster.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gdeal.brewmaster.dto.BrewMethodDTO;
import com.gdeal.brewmaster.model.BrewMethod;
import com.gdeal.brewmaster.repository.BrewMethodRepository;

@Service
public class BrewMethodService {
    
    private final BrewMethodRepository brewMethodRepository;

    public BrewMethodService(BrewMethodRepository breMethodRepository) {

        this.brewMethodRepository = breMethodRepository;
    }

    public List<BrewMethodDTO> getAllBrewMethods() {

        return brewMethodRepository.findAll()
        .stream()
        .map(this::toDTO)
        .toList();
    }

    private BrewMethodDTO toDTO(BrewMethod brewMethod) {

        return new BrewMethodDTO(
            brewMethod.getId(),
            brewMethod.getName()
        );
    }
}
