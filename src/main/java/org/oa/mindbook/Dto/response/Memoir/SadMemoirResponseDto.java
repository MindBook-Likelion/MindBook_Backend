package org.oa.mindbook.Dto.response.Memoir;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.SadMemoir;
import org.oa.mindbook.Domain.Entity.User;
import org.oa.mindbook.Dto.response.MemoirComment.SadMemoirCommentResponseDto;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SadMemoirResponseDto {

    private Long userId;

    private Long sadMemoirId;

    private String nickName;

    private String createdAt;

    private String memory;

    private String impression;

    private String status;

    private List<SadMemoirCommentResponseDto> commentList;

    public static SadMemoirResponseDto of(User user, SadMemoir sadMemoir, List<SadMemoirCommentResponseDto> commentList) {
        return SadMemoirResponseDto.builder()
                .userId(user.getId())
                .sadMemoirId(sadMemoir.getSadMemoirId())
                .nickName(user.getNickName())
                .createdAt(sadMemoir.getCreatedAt())
                .memory(sadMemoir.getMemory())
                .impression(sadMemoir.getImpression())
                .status(sadMemoir.getStatus())
                .commentList(commentList)
                .build();
    }
}

