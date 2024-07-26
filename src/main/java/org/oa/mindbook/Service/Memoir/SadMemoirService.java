package org.oa.mindbook.Service.Memoir;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.SadMemoir;
import org.oa.mindbook.Dto.request.Memoir.CreateSadMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.SadMemoirResponseDto;
import org.oa.mindbook.Repository.Memoir.SadMemoirRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SadMemoirService {

    private final SadMemoirRepository sadMemoirRepository;

    @Transactional
    public SadMemoirResponseDto saveSadMemoir(CreateSadMemoirRequestDto createSadMemoirRequestDto) {

        SadMemoir sadMemoir = sadMemoirRepository.save(createSadMemoirRequestDto.toEntity());

        return SadMemoirResponseDto.of(sadMemoir);
    }

    @Transactional
    public SadMemoirResponseDto getSadMemoir(Long sadMemoirId) {
        SadMemoir sadMemoir = sadMemoirRepository.findById(sadMemoirId).orElseThrow();

        return SadMemoirResponseDto.of(sadMemoir);
    }
}
