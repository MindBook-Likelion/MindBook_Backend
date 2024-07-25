package org.oa.mindbook.Service.Memoir;

import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.SadMemoir;
import org.oa.mindbook.Dto.request.CreateSadMemoirRequestDto;
import org.oa.mindbook.Dto.response.SadMemoirResponseDto;
import org.oa.mindbook.Repository.Memoir.SadMemoirRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

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
}
