package com.infitry.laboratory.service.transaction;

import com.infitry.laboratory.service.jpa.MemberService;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionalTest {

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("@Transactional lazy load")
    public void lazyLoading() {
        // given
        var memberId = 1L;
        // when
        // then
        assertThrows(LazyInitializationException.class, () -> memberService.findMemberLazyWithoutTransactional(memberId), "LazyInitializationException 이 발생해야 한다.");
    }

    @Test
    @DisplayName("@Transactional dirty checking")
    public void dirtyChecking() {
        // given
        var memberId = 1L;
        // when
        var member = memberService.findMember(memberId);
        memberService.updateMemberWithoutTransactional(memberId);
        var updatedMember = memberService.findMember(memberId);
        // then
        assertEquals(updatedMember.getName(), member.getName(), "Dirty checking 이 동작하지 않아야 한다.");
    }
}
