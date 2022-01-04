package com.mikhail.TacoCloudApplication.database.repository;

import com.mikhail.TacoCloudApplication.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String> {

}
