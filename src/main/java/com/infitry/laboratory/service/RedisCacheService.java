package com.infitry.laboratory.service;

import com.infitry.laboratory.config.aop.HashesCacheEvict;
import com.infitry.laboratory.config.aop.HashesCacheable;
import com.infitry.laboratory.dto.MemberDto;
import com.infitry.laboratory.persistence.jpa.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RedisCacheService {
    private final MemberRepository memberRepository;
    @Cacheable(cacheNames = "testCache", cacheManager = "redisCacheManager")
    public MemberDto findCacheMember(Long id) {
        return MemberDto.from(memberRepository.findById(id).orElseThrow());
    }

    @HashesCacheable(cacheName = "memberCache", ttl = 3600)
    public MemberDto findCustomCache(Long id) {
        log.info("No use cache user: {}", id);
        return MemberDto.from(memberRepository.findById(id).orElseThrow());
    }

    @HashesCacheEvict(cacheName = "memberCache")
    public void evictCache() {
        log.info("Clear memberCache");
    }
}
