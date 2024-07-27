package org.oa.mindbook.Service.Memoir;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.JoyMemoir;
import org.oa.mindbook.Domain.Entity.MemoirComment.JoyMemoirComment;
import org.oa.mindbook.Dto.request.Memoir.CreateJoyMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.JoyMemoirListResponseDto;
import org.oa.mindbook.Dto.response.Memoir.JoyMemoirResponseDto;
import org.oa.mindbook.Dto.response.MemoirComment.JoyMemoirCommentResponseDto;
import org.oa.mindbook.Repository.Memoir.JoyMemoirRepository;
import org.oa.mindbook.Repository.MemoirComment.JoyMemoirCommentRepository;
import org.springframework.stereotype.Service;

import java.util.Dictionary;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class JoyMemoirService {

    private final JoyMemoirRepository joyMemoirRepository;
    private final JoyMemoirCommentRepository joyMemoirCommentRepository;

    @Transactional
    public void saveJoyMemoir(CreateJoyMemoirRequestDto createJoyMemoirRequestDto) {

        JoyMemoir joyMemoir = joyMemoirRepository.save(createJoyMemoirRequestDto.toEntity());
    }

    @Transactional
    public JoyMemoirResponseDto getJoyMemoir(Long joyMemoirId) {
        JoyMemoir joyMemoir = joyMemoirRepository.findById(joyMemoirId).orElseThrow();
        List<JoyMemoirCommentResponseDto> commentList = joyMemoirCommentRepository.findByJoyMemoirId(joyMemoirId).stream().map(
                comment -> {
                    return JoyMemoirCommentResponseDto.builder()
                            .joyMemoirCommentId(comment.getJoyMemoirCommentId())
//                            .nickname(comment.getUser().getUserId())
                            .nickname("nickname")
                            .content(comment.getContent())
                            .createdAt(comment.getCreatedAt())
                            .build();
                }
        ).collect(Collectors.toList());

        return JoyMemoirResponseDto.of(joyMemoir, commentList);
    }

    @Transactional
    public List<JoyMemoirListResponseDto> getJoyMemoirList(String status) {
        List<JoyMemoir> joyMemoir = joyMemoirRepository.findByStatus(status);

        List<JoyMemoirListResponseDto> responseDtoList = joyMemoir.stream()
                .map(JoyMemoirListResponseDto::of)
                .collect(Collectors.toList());

        return responseDtoList;
    }
}
