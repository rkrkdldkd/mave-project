package com.maveProject.mave.service;

import com.maveProject.mave.domain.Answer;
import com.maveProject.mave.domain.Group;
import com.maveProject.mave.domain.Member;
import com.maveProject.mave.domain.Question;
import com.maveProject.mave.repository.AnswerRepository;
import com.maveProject.mave.restController.AnswerApiController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;


    @Transactional
    public void createAnswer(Member member, Group group, Question question, Answer answer){
        answer.registMember(member);
        answer.addAnswer(question);
        answer.registGroup(group);
        answerRepository.save(answer);
    }

    public List<AnswerApiController.AllAnswerResponse> findAllAnswer(Long groupId, Long questionNumber){
        return answerRepository.findAllAnswerQuery(groupId, questionNumber);
    }











}
