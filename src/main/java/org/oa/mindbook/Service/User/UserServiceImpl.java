package org.oa.mindbook.Service.User;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.User;
import org.oa.mindbook.Dto.request.User.UserReqDto;
import org.oa.mindbook.Repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Long save(UserReqDto userReqDto) {
        User user = User.builder()
                .nickName(userReqDto.getNickName())
                .userId(userReqDto.getUserId())
                .password(userReqDto.getPassword())
                .profileImage(userReqDto.getProfileImage())
                .build();

        User saveUser = userRepository.save(user);
        return saveUser.getId();
    }
}
