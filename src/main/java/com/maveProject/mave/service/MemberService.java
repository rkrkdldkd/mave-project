package com.maveProject.mave.service;

import com.maveProject.mave.domain.Member;
import com.maveProject.mave.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;


    @Transactional
    public Long MemberJoin(Member member){
        memberRepository.save(member);
        return member.getId();
    }

}
