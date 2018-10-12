package com.resort.springbootMolvenoLake.repositories;

import com.resort.springbootMolvenoLake.models.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
}
