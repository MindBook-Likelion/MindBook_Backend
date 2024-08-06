package org.oa.mindbook.Dto.response.Memoir;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.AngryMemoir;
import org.oa.mindbook.Domain.Entity.User.User;
import org.oa.mindbook.Dto.response.MemoirComment.AngryMemoirCommentResponseDto;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AngryMemoirResponseDto {

    private Long userId;

    private Long angryMemoirId;

    private String nickName;

    private String createdAt;

    private String memory;

    private String status;

    private List<AngryMemoirCommentResponseDto> commentList;

    public static AngryMemoirResponseDto of(AngryMemoir angryMemoir, List<AngryMemoirCommentResponseDto> commentList, User user) {
        return AngryMemoirResponseDto.builder()
                .userId(user.getId())
                .angryMemoirId(angryMemoir.getAngryMemoirId())
                .nickName(user.getNickName())
                .createdAt(angryMemoir.getCreatedAt())
                .memory(angryMemoir.getMemory())
                .status(angryMemoir.getStatus())
                .commentList(commentList)
                .build();
    }
}
