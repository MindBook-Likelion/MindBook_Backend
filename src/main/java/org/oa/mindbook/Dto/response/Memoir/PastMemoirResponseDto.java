package org.oa.mindbook.Dto.response.Memoir;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.PastMemoir;
import org.oa.mindbook.Domain.Entity.User.User;
import org.oa.mindbook.Dto.response.MemoirComment.PastMemoirCommentResponseDto;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PastMemoirResponseDto {

    private Long userId;

    private Long pastMemoirId;

    private String nickName;

    private String createdAt;

    private String pastAt;

    private String memory;

    private String status;

    private List<PastMemoirCommentResponseDto> commentList;

    public static PastMemoirResponseDto of(PastMemoir pastMemoir, List<PastMemoirCommentResponseDto> commentList, User user) {
        return PastMemoirResponseDto.builder()
                .userId(user.getId())
                .pastMemoirId(pastMemoir.getPastMemoirId())
                .nickName(user.getNickName())
                .createdAt(pastMemoir.getCreatedAt())
                .pastAt(pastMemoir.getPastAt())
                .memory(pastMemoir.getMemory())
                .status(pastMemoir.getStatus())
                .commentList(commentList)
                .build();
    }
}
