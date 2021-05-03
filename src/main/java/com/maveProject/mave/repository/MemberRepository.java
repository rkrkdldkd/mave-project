package com.maveProject.mave.repository;

import com.maveProject.mave.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Repository
public class MemberRepository {

    private final EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

   public Member findById(Long memberId){
       Member member = em.find(Member.class, memberId);
       return member;
   }

   public List<Member> findByName(String userName){
        return em.createQuery("select m from Member m where m.userName = :userName",
                Member.class)
                .setParameter("userName", userName)
                .getResultList();
   }
}
