package com.shop.shopadmin.service;

import com.shop.shopadmin.dto.NoticeDTO;
import com.shop.shopadmin.dto.NoticeFormDTO;
import com.shop.shopadmin.entity.Notice;
import com.shop.shopadmin.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public Long saveNotice(NoticeFormDTO noticeFormDTO) {
        Notice notice = noticeRepository.save(noticeFormDTO.toEntity());
        return notice.getNoticeId();
    }

    public NoticeDTO findOne(Long noticeId) {
        Optional<Notice> opWorkNotice = noticeRepository.findByNoticeId(noticeId);
        if (opWorkNotice.isEmpty()) {
            throw new NoSuchElementException("Notice not found: " + noticeId);
        }
        Notice notice = opWorkNotice.get();
        notice.addViews(1);
        noticeRepository.save(notice);
        return new NoticeDTO(
                notice.getNoticeId(),
                notice.getTitle(),
                notice.getContents(),
                notice.getCreatedBy(),
                notice.getCreated(),
                notice.getViews()
        );
    }

    public List<NoticeDTO> findAllNotice() {
        return noticeRepository.findAll(Sort.by(Sort.Direction.DESC, "noticeId"))
                .stream()
                .map(notice -> new NoticeDTO(
                        notice.getNoticeId(),
                        notice.getTitle(),
                        notice.getCreatedBy(),
                        notice.getCreated(),
                        notice.getViews()
                )).collect(Collectors.toList());
    }

    public Long updateNotice(NoticeFormDTO noticeFormDTO) {
        Optional<Notice> opWorkNotice = noticeRepository.findByNoticeId(noticeFormDTO.getNoticeId());
        if (opWorkNotice.isEmpty()) {
            throw new NoSuchElementException("Notice not found: " + noticeFormDTO.getNoticeId());
        }
        return  noticeRepository.save(noticeFormDTO.toEntity()).getNoticeId();
    }

    public void deleteNotice(Long noticeId) {
        noticeRepository.deleteById(noticeId);
    }


}
