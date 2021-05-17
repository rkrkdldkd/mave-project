package com.maveProject.mave.repository;

import com.maveProject.mave.domain.Group;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


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

    public List<Group> findByName(String name){
        return em.createQuery("select g from Group g where g.groupName = :name",
                 Group.class)
                .setParameter("name", name)
                .getResultList();
    }

}
