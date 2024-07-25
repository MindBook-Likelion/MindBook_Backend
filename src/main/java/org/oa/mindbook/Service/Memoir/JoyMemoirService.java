package org.oa.mindbook.Service.Memoir;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.JoyMemoir;
import org.oa.mindbook.Dto.request.CreateJoyMemoirRequestDto;
import org.oa.mindbook.Dto.response.JoyMemoirResponseDto;
import org.oa.mindbook.Repository.Memoir.JoyMemoirRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class JoyMemoirService {

    private final JoyMemoirRepository joyMemoirRepository;

    @Transactional
    public JoyMemoirResponseDto saveJoyMemoir(CreateJoyMemoirRequestDto createJoyMemoirRequestDto) {

        JoyMemoir joyMemoir = joyMemoirRepository.save(createJoyMemoirRequestDto.toEntity());

        return JoyMemoirResponseDto.of(joyMemoir);

    }
}
