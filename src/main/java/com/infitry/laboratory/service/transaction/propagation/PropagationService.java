package com.infitry.laboratory.service.transaction.propagation;

import com.infitry.laboratory.entity.Member;
import com.infitry.laboratory.persistence.jpa.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PropagationService {

    private final MemberRepository memberRepository;
    private final PropagationInternalService propagationInternalService;

    @Transactional
    public void requiredTransaction() {
        saveNewMember();
        try {
            propagationInternalService.required();
        } catch(RuntimeException e) {
            printLog(e);
        }
    }

    @Transactional
    public void requiresNewTransaction() {
        saveNewMember();
        try {
            propagationInternalService.requiresNew();
        } catch(RuntimeException e) {
            printLog(e);
        }
    }

    @Transactional
    public void supportsTransaction() {
        saveNewMember();
        try {
            propagationInternalService.supports();
        } catch(RuntimeException e) {
            printLog(e);
        }
    }

    @Transactional
    public void notSupportedTransactional() {
        saveNewMember();
        try {
            propagationInternalService.notSupported();
        } catch(RuntimeException e) {
            printLog(e);
        }
    }

    @Transactional
    public void nestedTransactional() {
        saveNewMember();
        try {
            propagationInternalService.nested();
        } catch(RuntimeException e) {
            printLog(e);
        }
    }

    @Transactional
    public void mandatoryTransactional() {
        saveNewMember();
        try {
            propagationInternalService.mandatory();
        } catch(RuntimeException e) {
            printLog(e);
        }
    }

    @Transactional
    public void neverTransactional() {
        saveNewMember();
        propagationInternalService.never();
    }

    private void saveNewMember() {
        Member member = new Member();
        member.setName("111");
        member.setNickName("32132");
        memberRepository.save(member);
    }

    private static void printLog(Exception e) {
        log.info("Exception 을 외부로 throw 하지 않는다.", e);
    }
}
