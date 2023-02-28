package com.shop.shopadmin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shop.shopadmin.error.NotEnoughStockException;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "item")
@Data
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "created_by", length = 20, updatable = false)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", updatable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date created;

    @Column(name = "updated_by", length = 20)
    private String updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date updated;

    // == 비즈니스 로직 ==

    /**
     * * stock 증가
     */
    public void addStock(int quantity)  {
        this.stockQuantity += quantity;
    }

    /*
     * stock 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if(restStock<0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
