package org.oa.mindbook.Dto.response.Memoir;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.JoyMemoir;
import org.oa.mindbook.Domain.Entity.User;
import org.oa.mindbook.Dto.response.MemoirComment.JoyMemoirCommentResponseDto;

import java.util.List;

@Slf4j
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoyMemoirResponseDto {

    private Long joyMemoirId;

    private String createdAt;

    private String memory;

    private String impression;

    private String status;

    private List<JoyMemoirCommentResponseDto> commentList;

    private Long userId;

    public static JoyMemoirResponseDto of(JoyMemoir joyMemoir, List<JoyMemoirCommentResponseDto> commentList, User user) {
        return JoyMemoirResponseDto.builder()
                .joyMemoirId(joyMemoir.getJoyMemoirId())
                .createdAt(joyMemoir.getCreatedAt())
                .memory(joyMemoir.getMemory())
                .impression(joyMemoir.getImpression())
                .status(joyMemoir.getStatus())
                .commentList(commentList)
                .userId(user.getId())
                .build();
    }

}
