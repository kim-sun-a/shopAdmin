package com.shop.shopadmin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.shopadmin.code.Constants;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity (name = "notice")
@Data
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long noticeId;

    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

    @Column(name = "views")
    private String views;

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


    // === 비즈니스 메서드 ===
    @PrePersist
    public void prePersist() {
        this.created= new Date();
        this.createdBy = Constants.principalUserName();
        this.preUpdate();
    }

    /**
     * 수정시 수정자, 수정일자 자동 생성*
     */
    @PreUpdate
    public void preUpdate() {
        this.updated = new Date();
        this.updatedBy = Constants.principalUserName();
    }

}
