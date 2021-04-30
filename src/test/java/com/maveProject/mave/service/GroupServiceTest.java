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

}