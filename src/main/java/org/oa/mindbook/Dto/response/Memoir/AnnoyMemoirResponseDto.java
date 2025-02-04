package org.oa.mindbook.Dto.response.Memoir;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.AnnoyMemoir;
import org.oa.mindbook.Domain.Entity.User.User;
import org.oa.mindbook.Dto.response.MemoirComment.AnnoyMemoirCommentResponseDto;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnoyMemoirResponseDto {

    private Long userId;

    private Long annoyMemoirId;

    private String nickName;

    private String createdAt;

    private String memory;

    private String status;

    private List<AnnoyMemoirCommentResponseDto> commentList;

    public static AnnoyMemoirResponseDto of(AnnoyMemoir annoyMemoir, List<AnnoyMemoirCommentResponseDto> commentList, User user) {
        return AnnoyMemoirResponseDto.builder()
                .userId(user.getId())
                .annoyMemoirId(annoyMemoir.getAnnoyMemoirId())
                .nickName(user.getNickName())
                .createdAt(annoyMemoir.getCreatedAt())
                .memory(annoyMemoir.getMemory())
                .status(annoyMemoir.getStatus())
                .commentList(commentList)
                .build();
    }
}
