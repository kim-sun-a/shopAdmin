package com.shop.shopadmin.dto;

import com.shop.shopadmin.entity.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@NoArgsConstructor
public class NoticeFormDTO {
    private Long noticeId;
    @NotEmpty(message = "제목은 필수입니다.")
    private String subject;
    @NotEmpty(message = "내용 작성은 필수입니다.")
    private String contents;
    private String attachFile;

    @Builder
    public NoticeFormDTO(String subject, String contents, String attachFile) {
        this.subject = subject;
        this.contents = contents;
        this.attachFile = attachFile;
    }

    public Notice toEntity() {
        return Notice.builder().title(subject).contents(contents).views(0).build();

    }

}
