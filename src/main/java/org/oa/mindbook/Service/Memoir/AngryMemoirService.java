package org.oa.mindbook.Service.Memoir;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.AngryMemoir;
import org.oa.mindbook.Dto.request.Memoir.CreateAngryMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.AngryMemoirListResponseDto;
import org.oa.mindbook.Dto.response.Memoir.AngryMemoirResponseDto;
import org.oa.mindbook.Dto.response.MemoirComment.AngryMemoirCommentResponseDto;
import org.oa.mindbook.Repository.Memoir.AngryMemoirRepository;
import org.oa.mindbook.Repository.MemoirComment.AngryMemoirCommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AngryMemoirService {

    private final AngryMemoirRepository angryMemoirRepository;
    private final AngryMemoirCommentRepository angryMemoirCommentRepository;

    @Transactional
    public void saveAngryMemoir(CreateAngryMemoirRequestDto createAngryMemoirRequestDto) {

        AngryMemoir angryMemoir = angryMemoirRepository.save(createAngryMemoirRequestDto.toEntity());
    }

    @Transactional
    public AngryMemoirResponseDto getAngryMemoir(Long angryMemoirId) {
        AngryMemoir angryMemoir = angryMemoirRepository.findById(angryMemoirId).orElseThrow();
        List<AngryMemoirCommentResponseDto> commentList = angryMemoirCommentRepository.findByAngryMemoirId(angryMemoirId).stream().map(
                comment -> {
                    return AngryMemoirCommentResponseDto.builder()
                            .angryMemoirCommentId(comment.getAngryMemoirCommentId())
//                            .nickname(comment.getUser().getUserId())
                            .nickname("nickname")
                            .content(comment.getContent())
                            .createdAt(comment.getCreatedAt())
                            .build();
                }
        ).collect(Collectors.toList());

        return AngryMemoirResponseDto.of(angryMemoir, commentList);
    }

    @Transactional
    public List<AngryMemoirListResponseDto> getAngryMemoirList(String status) {
        List<AngryMemoir> angryMemoir = angryMemoirRepository.findByStatus(status);

        List<AngryMemoirListResponseDto> responseDtoList = angryMemoir.stream()
                .map(AngryMemoirListResponseDto::of)
                .collect(Collectors.toList());

        return responseDtoList;
    }
}
