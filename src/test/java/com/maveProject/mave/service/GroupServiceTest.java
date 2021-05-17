package com.maveProject.mave.service;

import com.maveProject.mave.domain.Group;
import com.maveProject.mave.repository.GroupRepository;
import com.maveProject.mave.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GroupServiceTest {


    @Autowired
    GroupRepository groupRepository;

    @Autowired
    GroupService groupService;

    @Autowired
    EntityManager em;

    @Transactional
    @Test
    public void 그룹이름으로_가져오기(){
        //given
        List<Group> groups = groupRepository.findByName("그루비룸");
        Group group = groups.get(0);


        //when

        //then
    }

    @Transactional
    @Test
    public void minusCount(){
        //given
        Group group = new Group("Team1");
        em.persist(group);

        em.flush();
        em.clear();

        Group findGroup = em.find(Group.class, group.getId());

        groupService.minusCount(findGroup);

        System.out.println("findGroup = " + findGroup.getRemainCount());


        //when

        //then
    }

    @Transactional
    @Test
    public void setCount(){
        //given
        Group group = new Group("Team1");
        em.persist(group);

        em.flush();
        em.clear();

        Group findGroup = em.find(Group.class, group.getId());

        int count = groupService.setCount(findGroup);

        System.out.println("count = " + count);


        //when

        //then
    }

}