package org.oa.mindbook.Dto.response.Memoir;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.AnxietyMemoir;
import org.oa.mindbook.Domain.Entity.User;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnxietyMemoirListResponseDto {

    private Long anxietyMemoirId;

    private String nickName;

    private String createdAt;

    private String status;

    public AnxietyMemoirListResponseDto (AnxietyMemoir anxietyMemoir, User user) {
        this.anxietyMemoirId = anxietyMemoir.getAnxietyMemoirId();
        this.nickName = user.getNickName();
        this.createdAt = anxietyMemoir.getCreatedAt();
        this.status = anxietyMemoir.getStatus();
    }
}
