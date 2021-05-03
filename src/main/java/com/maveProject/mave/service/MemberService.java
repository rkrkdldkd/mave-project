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
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    public Long firstJoinGroup(Long userId,String groupName){
        Group group = new Group(groupName);
        groupRepository.save(group);
        Member member = memberRepository.findById(userId);
        member.SetGroup(group);
        return member.getGroup().getId();
    }

    @Transactional
    public Long JoinGroup(Long userId,Long groupId){
        Member member = memberRepository.findById(userId);
        Group group = groupRepository.findById(groupId);
        member.SetGroup(group);
        return member.getGroup().getId();
    }




}
