package com.shop.shopadmin.controller;

import com.shop.shopadmin.dto.NoticeDTO;
import com.shop.shopadmin.dto.NoticeFormDTO;
import com.shop.shopadmin.service.NoticeService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeService noticeService;

    @RequestMapping("/list")
    public String noticeList(Model model) {
        return "notice/list";
    }

    @RequestMapping("/{noticeId}")
    public String getNotice(@PathVariable("noticeId") Long noticeId, Model model) {
        NoticeDTO notice = noticeService.findOne(noticeId);
        model.addAttribute("notice", notice);
        return "notice/post";
    }

    /**
     * 공지사항 리스트
     * @return
     * @throws ParseException
     */
    @ResponseBody
    @GetMapping("/getList")
    public ResponseEntity<List<NoticeDTO>> getNoticeList() throws ParseException {
        return ResponseEntity.ok(noticeService.findAllNotice());
    }

    /**
     * 공지사항 신규 작성
     * @param noticeFormDTO
     * @return
     */
    @ResponseBody
    @PostMapping(value="/write")
    public CreateNoticeResponse saveNotice(@Valid @RequestBody NoticeFormDTO noticeFormDTO) {
        return new CreateNoticeResponse(noticeService.saveNotice(noticeFormDTO));
    }

    /**
     * 공지사항 수정 작성
     * @param noticeFormDTO
     * @return
     */
    @ResponseBody
    @PutMapping(value="/edit")
    public CreateNoticeResponse editNotice(@Valid @RequestBody NoticeFormDTO noticeFormDTO) {
        return new CreateNoticeResponse(noticeService.updateNotice(noticeFormDTO));
    }

    @Data
    static class CreateNoticeResponse {
        private Long id;

        public CreateNoticeResponse(Long id) {
            this.id = id;
        }
    }


}
