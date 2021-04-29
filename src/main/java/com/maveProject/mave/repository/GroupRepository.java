package com.maveProject.mave.repository;

import com.maveProject.mave.domain.Group;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


@Getter
@RequiredArgsConstructor
@Repository
public class GroupRepository {

    private final EntityManager em;

    public Long save(Group group){
        em.persist(group);
        return group.getId();
    }

    public Group findById(Long groupId){
        Group group = em.find(Group.class, groupId);
        return group;
    }

}
