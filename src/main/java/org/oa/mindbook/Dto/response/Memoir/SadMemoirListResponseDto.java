package org.oa.mindbook.Dto.response.Memoir;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.SadMemoir;
import org.oa.mindbook.Domain.Entity.User.User;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SadMemoirListResponseDto {

    private Long sadMemoirId;

    private String nickName;

    private String createdAt;

    private String status;


    public SadMemoirListResponseDto (SadMemoir sadMemoir, User user) {
        this.sadMemoirId = sadMemoir.getSadMemoirId();
        this.nickName = user.getNickName();
        this.createdAt = sadMemoir.getCreatedAt();
        this.status = sadMemoir.getStatus();
    }
}
