package org.oa.mindbook.Service.Memoir;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.AnxietyMemoir;
import org.oa.mindbook.Dto.request.Memoir.CreateAnxietyMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.AnxietyMemoirListResponseDto;
import org.oa.mindbook.Dto.response.Memoir.AnxietyMemoirResponseDto;
import org.oa.mindbook.Repository.Memoir.AnxietyMemoirRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public AnxietyMemoirResponseDto getAnxietyMemoir(Long anxietyMemoirId) {
        AnxietyMemoir anxietyMemoir = anxietyMemoirRepository.findById(anxietyMemoirId).orElseThrow();

        return AnxietyMemoirResponseDto.of(anxietyMemoir);
    }

    @Transactional
    public List<AnxietyMemoirListResponseDto> getAnxietyMemoirList(String status) {
        List<AnxietyMemoir> anxietyMemoir = anxietyMemoirRepository.findByStatus(status);

        List<AnxietyMemoirListResponseDto> responseDtoList = anxietyMemoir.stream()
                .map(AnxietyMemoirListResponseDto::of)
                .collect(Collectors.toList());

        return responseDtoList;
    }
}
