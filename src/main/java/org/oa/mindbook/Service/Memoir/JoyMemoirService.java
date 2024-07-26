package org.oa.mindbook.Service.Memoir;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.JoyMemoir;
import org.oa.mindbook.Dto.request.Memoir.CreateJoyMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.JoyMemoirListResponseDto;
import org.oa.mindbook.Dto.response.Memoir.JoyMemoirResponseDto;
import org.oa.mindbook.Repository.Memoir.JoyMemoirRepository;
import org.springframework.stereotype.Service;

import java.util.Dictionary;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public JoyMemoirResponseDto getJoyMemoir(Long joyMemoirId) {
        JoyMemoir joyMemoir = joyMemoirRepository.findById(joyMemoirId).orElseThrow();

        return JoyMemoirResponseDto.of(joyMemoir);
    }

    @Transactional
    public List<JoyMemoirListResponseDto> getJoyMemoirList(String status) {
        List<JoyMemoir> joyMemoir = joyMemoirRepository.findByStatus(status);

        List<JoyMemoirListResponseDto> responseDtoList = joyMemoir.stream()
                .map(JoyMemoirListResponseDto::of)
                .collect(Collectors.toList());

        return responseDtoList;
    }
}
