package com.maveProject.mave.service;

import com.maveProject.mave.domain.Group;
import com.maveProject.mave.domain.Member;
import com.maveProject.mave.repository.GroupRepository;
import com.maveProject.mave.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final GroupRepository groupRepository;


    @Transactional
    public Long joinMember(Member member){
        Long memberId = memberRepository.save(member);
        return memberId;
    }

    @Transactional
    public Long joinGroup(Member member,Group group){
        member.SetGroup(group);
        return member.getGroup().getId();
    }

    public Member findMember(Long memberId){
        Member member = memberRepository.findById(memberId);
        return member;
    }




}
