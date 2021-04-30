package com.maveProject.mave.restController;

import com.maveProject.mave.domain.Group;
import com.maveProject.mave.domain.Member;
import com.maveProject.mave.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberApiControllerTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Transactional
    @Test
    public void 처음_그룹_만들기(){
        Member member = new Member("hello1");
        em.persist(member);
        //given
        Group group = new Group("그룹이룸");
        em.persist(group);

        Group group1 = em.find(Group.class, group.getId());

        member.SetGroup(group1);

        em.find(Member.class,member.getId());

        em.flush();
        //when

        //then
    }

}