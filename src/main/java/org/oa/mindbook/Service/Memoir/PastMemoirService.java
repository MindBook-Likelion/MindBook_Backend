package org.oa.mindbook.Service.Memoir;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.PastMemoir;
import org.oa.mindbook.Dto.request.CreatePastMemoirRequestDto;
import org.oa.mindbook.Dto.response.PastMemoirResponseDto;
import org.oa.mindbook.Repository.Memoir.PastMemoirRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PastMemoirService {

    private final PastMemoirRepository pastMemoirRepository;

    @Transactional
    public PastMemoirResponseDto savePastMemoir(CreatePastMemoirRequestDto createPastMemoirRequestDto) {

        PastMemoir pastMemoir = pastMemoirRepository.save(createPastMemoirRequestDto.toEntity());

        return PastMemoirResponseDto.of(pastMemoir);
    }
}
