package org.oa.mindbook.Dto.response.Memoir;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.AnxietyMemoir;
import org.oa.mindbook.Domain.Entity.User.User;
import org.oa.mindbook.Dto.response.MemoirComment.AnxietyMemoirCommentResponseDto;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnxietyMemoirResponseDto {

    private Long userId;

    private Long anxietyMemoirId;

    private String nickName;

    private String createdAt;

    private String memory;

    private String status;

    private List<AnxietyMemoirCommentResponseDto> commentList;

    public static AnxietyMemoirResponseDto of(AnxietyMemoir anxietyMemoir, List<AnxietyMemoirCommentResponseDto> commentList, User user) {
        return AnxietyMemoirResponseDto.builder()
                .userId(user.getId())
                .anxietyMemoirId(anxietyMemoir.getAnxietyMemoirId())
                .nickName(user.getNickName())
                .createdAt(anxietyMemoir.getCreatedAt())
                .memory(anxietyMemoir.getMemory())
                .status(anxietyMemoir.getStatus())
                .commentList(commentList)
                .build();
    }
}
