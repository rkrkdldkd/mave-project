package com.maveProject.mave.service;

import com.maveProject.mave.domain.Group;
import com.maveProject.mave.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GroupService {

    private final GroupRepository groupRepository;

    @Transactional
    public Group findGroup(String groupName){
        List<Group> groups = groupRepository.findByName(groupName);
        return groups.get(0);
    }



}