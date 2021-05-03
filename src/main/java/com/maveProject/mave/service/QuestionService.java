package com.maveProject.mave.service;

import com.maveProject.mave.domain.Group;
import com.maveProject.mave.domain.Question;
import com.maveProject.mave.repository.GroupRepository;
import com.maveProject.mave.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final GroupRepository groupRepository;

    @Transactional
    public Long createQuestion(Long groupId,String questionContent, Long questionNumber){
        Group group = groupRepository.findById(groupId);
        Question question = new Question(group, questionContent,questionNumber);
        Long questionId = questionRepository.save(question);
        return questionId;
    }

    public Question findQuestion(Long groupId, Long questionNumber){
        Group group = groupRepository.findById(groupId);
        Question question = questionRepository.findByNumberForGroup(group.getId(),questionNumber).get(0);
        return question;

    }


}
