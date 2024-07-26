package org.oa.mindbook.Service.Memoir;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.AngryMemoir;
import org.oa.mindbook.Dto.request.CreateAngryMemoirRequestDto;
import org.oa.mindbook.Dto.request.CreateAnnoyMemoirRequestDto;
import org.oa.mindbook.Dto.response.AngryMemoirResponseDto;
import org.oa.mindbook.Repository.Memoir.AngryMemoirRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AngryMemoirService {

    private final AngryMemoirRepository angryMemoirRepository;

    @Transactional
    public AngryMemoirResponseDto saveAngryMemoir(CreateAngryMemoirRequestDto createAngryMemoirRequestDto) {

        AngryMemoir angryMemoir = angryMemoirRepository.save(createAngryMemoirRequestDto.toEntity());

        return AngryMemoirResponseDto.of(angryMemoir);
    }

    @Transactional
    public AngryMemoirResponseDto getAngryMemoir(Long angryMemoirId) {
        AngryMemoir angryMemoir = angryMemoirRepository.findById(angryMemoirId).orElseThrow();

        return AngryMemoirResponseDto.of(angryMemoir);
    }
}
