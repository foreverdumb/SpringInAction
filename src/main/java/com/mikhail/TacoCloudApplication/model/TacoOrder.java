package com.mikhail.TacoCloudApplication.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Table
public class TacoOrder {

    @Id
    private Long id;

    @Column(value = "created_at")
    private Date createdAt = new Date();

    @Column(value = "delivery_name")
    @NotBlank(message = "You have to fill this field.")
    private String deliveryName;

    @Column(value = "delivery_street")
    @NotBlank(message = "You have to fill this field.")
    private String deliveryStreet;

    @Column(value = "delivery_city")
    @NotBlank(message = "You have to fill this field.")
    private String deliveryCity;

    @Column(value = "delivery_state")
    @NotBlank(message = "You have to fill this field.")
    private String deliveryState;

    @Column(value = "delivery_zip")
    @NotBlank(message = "You have to fill this field.")
    private String deliveryZip;

    @Column(value = "cc_number")
    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Column(value = "cc_expiration")
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;

    @Column(value = "cc_cvv")
    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    @Column(value = "placed_at")
    private Date placedAt;

    @Column
    private List<Taco> tacos = new ArrayList<>();

    @Column
    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }

}
