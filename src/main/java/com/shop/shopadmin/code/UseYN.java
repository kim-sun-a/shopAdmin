package com.shop.shopadmin.code;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UseYN {
    Y, N;

    @JsonCreator
    public static UseYN from(String s) {
        return UseYN.valueOf(s.toUpperCase());
    }

}
