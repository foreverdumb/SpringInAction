package com.mikhail.TacoCloudApplication.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@Entity
@Table(name = "taco_order")
public class TacoOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "delivery_name")
    @NotBlank(message = "You have to fill this field.")
    private String deliveryName;

    @Column(name = "delivery_street")
    @NotBlank(message = "You have to fill this field.")
    private String deliveryStreet;

    @Column(name = "delivery_city")
    @NotBlank(message = "You have to fill this field.")
    private String deliveryCity;

    @Column(name = "delivery_state")
    @NotBlank(message = "You have to fill this field.")
    private String deliveryState;

    @Column(name = "delivery_zip")
    @NotBlank(message = "You have to fill this field.")
    private String deliveryZip;

    @Column(name = "cc_number")
    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Column(name = "cc_expiration")
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Column(name = "cc_cvv")
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    @Column(name = "placed_at")
    private Date placedAt;

    @Column
    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Taco> tacos = new ArrayList<>();

    @PrePersist
    private void initPlacedAt() {
        placedAt = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TacoOrder tacoOrder = (TacoOrder) o;
        return id != null && Objects.equals(id, tacoOrder.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}
