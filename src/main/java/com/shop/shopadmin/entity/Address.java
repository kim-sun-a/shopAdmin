package com.shop.shopadmin.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Address {
    private String zipcode;
    private String address;
    private String detailAddress;

    protected Address() {
    }

    public Address(String zipcode, String address, String detailAddress) {
        this.zipcode = zipcode;
        this.address = address;
        this.detailAddress = detailAddress;
    }
}
