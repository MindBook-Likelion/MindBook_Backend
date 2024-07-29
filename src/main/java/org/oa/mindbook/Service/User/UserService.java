package org.oa.mindbook.Service.User;

import org.oa.mindbook.Dto.request.User.UserReqDto;

public interface UserService {
    Long save(UserReqDto userReqDto);
}
