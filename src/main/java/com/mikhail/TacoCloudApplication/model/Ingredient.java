package com.mikhail.TacoCloudApplication.model;

import lombok.Data;

import java.util.Date;

@Data
public class Ingredient {

    private final String id;
    private Date createdAt = new Date();
    private final String Name;
    private final Type type;

    public enum Type {
        WRAP,
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE
    }

}
