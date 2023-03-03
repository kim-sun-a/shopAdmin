package com.shop.shopadmin.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class NoticeDTO {
    private Long noticeId;
    private String subject;
    private String contents;
    private String createdBy;
    private Date created;
    private Integer views;

    public NoticeDTO(Long noticeId, String subject, String contents, String createdBy, Date created, Integer views) {
        this.noticeId = noticeId;
        this.subject = subject;
        this.contents = contents;
        this.createdBy = createdBy;
        this.created = created;
        this.views = views;
    }

    public NoticeDTO(Long noticeId, String subject, String createdBy, Date created, Integer views) {
        this.noticeId = noticeId;
        this.subject = subject;
        this.createdBy = createdBy;
        this.created = created;
        this.views = views;
    }

}
