package com.gdeal.brewmaster.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gdeal.brewmaster.model.BrewMethod;

public interface BrewMethodRepository extends JpaRepository<BrewMethod, Long> {
    
    Optional<BrewMethod> findByNameIgnoreCase(String name);
}
