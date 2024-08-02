package org.oa.mindbook.Dto.response.Memoir;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.JoyMemoir;
import org.oa.mindbook.Domain.Entity.User.User;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JoyMemoirListResponseDto {

    private Long joyMemoirId;

    private String nickName;

    private String createdAt;

    private String status;

    public JoyMemoirListResponseDto(JoyMemoir joyMemoir, User user) {
        this.joyMemoirId = joyMemoir.getJoyMemoirId();
        this.nickName = user.getNickName();
        this.createdAt = joyMemoir.getCreatedAt();
        this.status = joyMemoir.getStatus();
    }



}
