package com.mikhail.TacoCloudApplication.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class TacoOrder {

    private Long id;

    private Date createdAt = new Date();

    @NotBlank(message = "You have to fill this field.")
    private String deliveryName;

    @NotBlank(message = "You have to fill this field.")
    private String deliveryStreet;

    @NotBlank(message = "You have to fill this field.")
    private String deliveryCity;

    @NotBlank(message = "You have to fill this field.")
    private String deliveryState;

    @NotBlank(message = "You have to fill this field.")
    private String deliveryZip;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    private Date placedAt;

    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }

}
