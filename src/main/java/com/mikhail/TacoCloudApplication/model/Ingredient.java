package com.mikhail.TacoCloudApplication.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table(value = "ingredient")
public class Ingredient {

    @Id
    private final String id;
    @Column(value = "created_at")
    private Date createdAt = new Date();
    @Column(value = "name")
    private final String Name;
    @Column(value = "type")
    private final Type type;

    public enum Type {
        WRAP,
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE
    }

}
