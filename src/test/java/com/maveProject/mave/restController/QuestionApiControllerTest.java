package com.maveProject.mave.restController;

import com.maveProject.mave.domain.Group;
import com.maveProject.mave.domain.Member;
import com.maveProject.mave.service.GroupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionApiControllerTest {

    @Autowired
    GroupService groupService;

    @Autowired
    EntityManager em;

    @Transactional
    @Test
    public void 카운트테스트(){
        //given
        Member member1 = new Member("멤버1");
        Member member2 = new Member("멤버2");
        em.persist(member1);
        em.persist(member2);

        Group group = new Group("그룹1");
        em.persist(group);

        member1.SetGroup(group);
        member2.SetGroup(group);

        System.out.println("group.getMembers().get(0) = " + group.getMembers().get(0).getUserName());
        System.out.println("group.getMembers().get(0) = " + group.getMembers().get(1).getUserName());

        int count = groupService.setCount(group);
        System.out.println("count = " + count);
        //when

        //then
    }

}