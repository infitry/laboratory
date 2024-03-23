package com.infitry.laboratory.service.jpa;

import com.infitry.laboratory.entity.Member;
import com.infitry.laboratory.persistence.jpa.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Member findMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        member.getMemberGroups().forEach(it -> System.out.println(it.getCode()));
        return member;
    }

    public void findMemberLazyWithoutTransactional(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        member.getMemberGroups().forEach(it -> System.out.println(it.getCode()));
    }

    public void updateMemberWithoutTransactional(Long memberId) {
        var member = memberRepository.findById(memberId).orElseThrow();
        member.setName("tester2");
        member.setAge(1);
    }
}
