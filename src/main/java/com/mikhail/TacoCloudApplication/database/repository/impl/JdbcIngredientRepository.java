package com.mikhail.TacoCloudApplication.database.repository.impl;

import com.mikhail.TacoCloudApplication.database.repository.IngredientRepository;
import com.mikhail.TacoCloudApplication.database.repository.mapper.IngredientMapper;
import com.mikhail.TacoCloudApplication.model.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

    private final JdbcTemplate jdbcTemplate;
    private final IngredientMapper ingredientMapper;

    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate, IngredientMapper ingredientMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.ingredientMapper = ingredientMapper;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbcTemplate.query("", ingredientMapper);
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return null;
    }
}
