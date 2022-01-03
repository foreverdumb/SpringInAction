package com.mikhail.TacoCloudApplication.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Table(value = "taco")
public class Taco {

    @Id
    private Long id;

    @Column(value = "created_at")
    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5, message = "The taco's length has to be 5 characters long or more.")
    @Column(value = "name")
    private String name;

    @NotNull
    @Size(min = 1, message = "You have to choose at least 1 ingredient.")
    private List<IngredientRef> ingredientRefs;

}
