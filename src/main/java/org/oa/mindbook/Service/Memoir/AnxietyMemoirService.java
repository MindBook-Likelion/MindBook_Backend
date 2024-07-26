package org.oa.mindbook.Service.Memoir;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.AnxietyMemoir;
import org.oa.mindbook.Dto.request.CreateAnxietyMemoirRequestDto;
import org.oa.mindbook.Dto.response.AnxietyMemoirResponseDto;
import org.oa.mindbook.Repository.Memoir.AnxietyMemoirRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnxietyMemoirService {

    private final AnxietyMemoirRepository anxietyMemoirRepository;

    @Transactional
    public AnxietyMemoirResponseDto saveAnxietyMemoir(CreateAnxietyMemoirRequestDto createAnxietyMemoirRequestDto) {

        AnxietyMemoir anxietyMemoir = anxietyMemoirRepository.save(createAnxietyMemoirRequestDto.toEntity());

        return AnxietyMemoirResponseDto.of(anxietyMemoir);
    }

    @Transactional
    public AnxietyMemoirResponseDto getAnxiety(Long anxietyMemoirId) {
        AnxietyMemoir anxietyMemoir = anxietyMemoirRepository.findById(anxietyMemoirId).orElseThrow();

        return AnxietyMemoirResponseDto.of(anxietyMemoir);
    }
}
