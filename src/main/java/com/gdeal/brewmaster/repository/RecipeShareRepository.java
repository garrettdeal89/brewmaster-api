package com.gdeal.brewmaster.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gdeal.brewmaster.model.RecipeShare;
import java.util.UUID;


public interface RecipeShareRepository extends JpaRepository<RecipeShare, Long> {
    
    Optional<RecipeShare> findByToken(UUID token);
}
