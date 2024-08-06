package org.oa.mindbook.Dto.response.Memoir;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.JoyMemoir;
import org.oa.mindbook.Domain.Entity.User.User;
import org.oa.mindbook.Dto.response.MemoirComment.JoyMemoirCommentResponseDto;

import java.util.List;

@Slf4j
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoyMemoirResponseDto {

    private Long userId;

    private Long joyMemoirId;

    private String nickName;

    private String createdAt;

    private String memory;

    private String status;

    private List<JoyMemoirCommentResponseDto> commentList;

    public static JoyMemoirResponseDto of(JoyMemoir joyMemoir, List<JoyMemoirCommentResponseDto> commentList, User user) {
        return JoyMemoirResponseDto.builder()
                .userId(user.getId())
                .joyMemoirId(joyMemoir.getJoyMemoirId())
                .nickName(user.getNickName())
                .createdAt(joyMemoir.getCreatedAt())
                .memory(joyMemoir.getMemory())
                .status(joyMemoir.getStatus())
                .commentList(commentList)
                .build();
    }

}
