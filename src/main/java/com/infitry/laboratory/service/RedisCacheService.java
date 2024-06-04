package com.infitry.laboratory.service;

import com.infitry.laboratory.dto.MemberDto;
import com.infitry.laboratory.persistence.jpa.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RedisCacheService {
    private final MemberRepository memberRepository;
    @Cacheable(cacheNames = "testCache", cacheManager = "redisCacheManager")
    public MemberDto findCacheMember(Long id) {
        return MemberDto.from(memberRepository.findById(id).orElseThrow());
    }
}
