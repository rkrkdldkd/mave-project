package com.maveProject.mave.service;

import com.maveProject.mave.domain.Group;
import com.maveProject.mave.domain.Question;
import com.maveProject.mave.repository.QuestionRepository;
import com.maveProject.mave.restController.QuestionApiController;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Transactional
    public Long createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question findQuestion(Group group, Long questionNumber) {
        return questionRepository.findByNumberForGroupQuery(group.getId(), questionNumber).get(0);
    }

    public List<QuestionApiController.GiveAllQuestionResponse> findAllQuestion(Group group, Long questionNumber) {
        return questionRepository.findAllQuestionForGroup(group.getId(), questionNumber);
    }





}
