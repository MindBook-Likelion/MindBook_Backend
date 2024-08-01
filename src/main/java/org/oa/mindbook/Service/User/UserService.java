package org.oa.mindbook.Service.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.User.User;
import org.oa.mindbook.Dto.request.User.CreateUserRequestDto;
import org.oa.mindbook.Dto.request.User.UpdateUserRequestDto;
import org.oa.mindbook.Dto.response.User.UserResponseDto;
import org.oa.mindbook.Repository.User.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDto createUser(CreateUserRequestDto createUserRequestDto) {
        User user = createUserRequestDto.toEntity(passwordEncoder);
        Optional<User> findUser = userRepository.findByEmail(createUserRequestDto.getEmail());
        if (findUser.isPresent()) {
            throw new RuntimeException("이미 존재하는 유저입니다.");
        }
        userRepository.save(user);
        return UserResponseDto.from(user);
    }
    public UserResponseDto getUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("사용자가 존재히지 않습니다."));
        return UserResponseDto.from(user);
    }
    @Transactional
    public UserResponseDto updateUser(String email, UpdateUserRequestDto userRequestDto) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        user.update(userRequestDto);
        userRepository.save(user);
        return UserResponseDto.from(user);

    }
    @Transactional
    public void deleteUser(String email) {
        userRepository.deleteByEmail(email);
    }
}