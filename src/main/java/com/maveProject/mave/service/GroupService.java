package com.maveProject.mave.service;

import com.maveProject.mave.domain.Group;
import com.maveProject.mave.domain.IsFinish;
import com.maveProject.mave.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GroupService {

    private final GroupRepository groupRepository;

    @Transactional
    public Long saveGroup(Group group){
        Long groupId = groupRepository.save(group);
        return groupId;
    }

    @Transactional
    public Group findGroup(Long groupId){
        Group group =  groupRepository.findById(groupId);
        return group;
    }

    @Transactional
    public int setCount(Long groupId){
        Group group = groupRepository.findById(groupId);
        group.setRemainCountCount(group.getMembers().size());
        return group.getRemainCount();
    }

    @Transactional
    public void minusCount(Group group){
        if(group.getRemainCount() == 0){
            return;
        }
        group.setRemainCountCount(group.getRemainCount()-1);
    }

    @Transactional
    public Boolean compareState(Group group){
        if(group.getRemainCount() == 0){
            group.setStatus(IsFinish.YES);
            return true;
        }

        return false;
    }



}
