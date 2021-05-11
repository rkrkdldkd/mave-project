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
    public Long createQuestion(Question question){
        Long questionId = questionRepository.save(question);
        return questionId;
    }

    public Question findQuestion(Group group, Long questionNumber){
        Question question = questionRepository.findByNumberForGroupQuery(group.getId(),questionNumber).get(0);
        return question;
    }


}
