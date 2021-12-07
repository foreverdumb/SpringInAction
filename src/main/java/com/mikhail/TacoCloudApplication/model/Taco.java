package com.mikhail.TacoCloudApplication.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Taco {

    private Long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5, message = "The taco's length has to be 5 characters long or more.")
    private String name;

    @NotNull
    @Size(min = 1, message = "You have to choose at least 1 ingredient.")
    private List<Ingredient> ingredients;

}
