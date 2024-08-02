package org.oa.mindbook.Dto.response.Memoir;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.AnnoyMemoir;
import org.oa.mindbook.Domain.Entity.User.User;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnnoyMemoirListResponseDto {

    private Long annoyMemoirId;

    private String nickName;

    private String createdAt;

    private String status;

    public AnnoyMemoirListResponseDto (AnnoyMemoir annoyMemoir, User user) {
        this.annoyMemoirId = annoyMemoir.getAnnoyMemoirId();
        this.nickName = user.getNickName();
        this.createdAt = annoyMemoir.getCreatedAt();
        this.status = annoyMemoir.getStatus();
    }
}
