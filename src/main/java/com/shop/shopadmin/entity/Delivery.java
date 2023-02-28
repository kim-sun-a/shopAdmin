package com.shop.shopadmin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.shopadmin.code.DeliveryStatus;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "delivery")
@Data
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    @JsonIgnore
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;  //READY, COMP
}
