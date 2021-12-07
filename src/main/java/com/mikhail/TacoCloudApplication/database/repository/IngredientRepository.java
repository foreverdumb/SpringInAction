package com.mikhail.TacoCloudApplication.database.repository;

import com.mikhail.TacoCloudApplication.model.Ingredient;

import java.util.Optional;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
}
