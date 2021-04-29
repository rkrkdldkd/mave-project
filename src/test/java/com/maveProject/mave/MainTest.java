package com.maveProject.mave;

import com.maveProject.mave.domain.Group;
import com.maveProject.mave.domain.Member;
import com.maveProject.mave.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
public class MainTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Transactional
    @Test
    public void 메인테스트(){
        Member member = new Member();
        em.persist(member);
        //given
        Group group = new Group();
        em.persist(group);

        Group group1 = em.find(Group.class, group.getId());

        member.SetGroup(group1);

        em.find(Member.class,member.getId());

        em.flush();
        //when

        //then
    }
}
