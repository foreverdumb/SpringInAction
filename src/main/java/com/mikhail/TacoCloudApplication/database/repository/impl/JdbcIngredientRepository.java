package com.mikhail.TacoCloudApplication.database.repository.impl;

import com.mikhail.TacoCloudApplication.database.repository.IngredientRepository;
import com.mikhail.TacoCloudApplication.mapper.IngredientMapper;
import com.mikhail.TacoCloudApplication.model.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
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
    public List<Ingredient> findAll() {
        return jdbcTemplate.query(""
                + "SELECT *\n"
                + "FROM ingredient", ingredientMapper
        );
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        List<Ingredient> ingredientList =
                jdbcTemplate.query(""
                        + "SELECT *\n"
                        + "FROM ingredien\n"
                        + "WHERE id = ?", ingredientMapper
                );
        return ingredientList.size() == 0 ? Optional.empty() : Optional.of(ingredientList.get(0));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update(
                "INSERT INTO ingredient (id, name, type) VALUES (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString()
        );
        return ingredient;
    }
}
