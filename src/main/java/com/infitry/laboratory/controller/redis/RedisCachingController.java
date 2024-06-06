package com.infitry.laboratory.controller.redis;

import com.infitry.laboratory.dto.MemberDto;
import com.infitry.laboratory.service.RedisCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis/cache")
@RequiredArgsConstructor
public class RedisCachingController {
    private final RedisCacheService redisCacheService;
    @GetMapping("/{key}")
    public MemberDto cacheData(@PathVariable Long key) {
        return redisCacheService.findCacheMember(key);
    }
    @GetMapping("/hashes/{key}")
    public MemberDto customCacheData(@PathVariable Long key) {
        return redisCacheService.findCustomCache(key);
    }

    @GetMapping("/hashes/clear")
    public void clearCache() {
        redisCacheService.evictCache();
    }
}
