package com.sion.minikurlyback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sion.minikurlyback.enums.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {
    @Id @GeneratedValue
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private AddressDetail addressDetail;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
}

