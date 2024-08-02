package org.oa.mindbook.Dto.response.Memoir;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.AngryMemoir;
import org.oa.mindbook.Domain.Entity.User.User;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AngryMemoirListResponseDto {

    private Long angryMemoirId;

    private String nickName;

    private String createdAt;

    private String status;

    public AngryMemoirListResponseDto (AngryMemoir angryMemoir, User user) {
        this.angryMemoirId = angryMemoir.getAngryMemoirId();
        this.nickName = user.getNickName();
        this.createdAt = angryMemoir.getCreatedAt();
        this.status = angryMemoir.getStatus();
    }
}
