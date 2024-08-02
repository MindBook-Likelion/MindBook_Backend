package org.oa.mindbook.Dto.response.Memoir;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.PastMemoir;
import org.oa.mindbook.Domain.Entity.User.User;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PastMemoirListResponseDto {

    private Long pastMemoirId;

    private String nickName;

    private String createdAt;

    private String status;

    public PastMemoirListResponseDto (PastMemoir pastMemoir, User user) {
        this.pastMemoirId = pastMemoir.getPastMemoirId();
        this.nickName = user.getNickName();
        this.createdAt = pastMemoir.getCreatedAt();
        this.status = pastMemoir.getStatus();
    }
}
