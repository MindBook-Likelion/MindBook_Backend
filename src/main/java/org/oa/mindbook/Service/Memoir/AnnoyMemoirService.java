package org.oa.mindbook.Service.Memoir;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.AnnoyMemoir;
import org.oa.mindbook.Dto.request.CreateAnnoyMemoirRequestDto;
import org.oa.mindbook.Dto.response.AnnoyMemoirResponseDto;
import org.oa.mindbook.Repository.Memoir.AnnoyMemoirRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnnoyMemoirService {

    private final AnnoyMemoirRepository annoyMemoirRepository;

    @Transactional
    public AnnoyMemoirResponseDto saveAnnoyMemoir(CreateAnnoyMemoirRequestDto createAnnoyMemoirRequestDto) {

        AnnoyMemoir annoyMemoir = annoyMemoirRepository.save(createAnnoyMemoirRequestDto.toEntity());

        return AnnoyMemoirResponseDto.of(annoyMemoir);
    }

    @Transactional
    public AnnoyMemoirResponseDto getAnnoyMemoir(Long annoyMemoirId) {
        AnnoyMemoir annoyMemoir = annoyMemoirRepository.findById(annoyMemoirId).orElseThrow();

        return AnnoyMemoirResponseDto.of(annoyMemoir);
    }
}
