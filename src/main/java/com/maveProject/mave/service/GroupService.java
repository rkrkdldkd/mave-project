package com.maveProject.mave.service;

import com.maveProject.mave.domain.Group;
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
       return  groupRepository.save(group);

    }

    @Transactional
    public Group findGroup(Long groupId){
        return groupRepository.findById(groupId);
    }

    @Transactional
    public int setCount(Group group){
      return group.setCount();
    }

    @Transactional
    public void minusCount(Group group){
        group.minusCount();
    }

    @Transactional
    public Boolean compareState(Group group){
        return group.compareState();
    }

    @Transactional
    public Boolean compareDate(Group group){
        Long prevDate = group.getDiaryDate();
        Long changedDate = group.changeDiaryDate();
//        Long changedDate = 3l; 테스트용!
        return group.isDateChanged(prevDate, changedDate);
    }

}
