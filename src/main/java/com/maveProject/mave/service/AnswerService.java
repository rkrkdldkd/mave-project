package com.maveProject.mave.service;

import com.maveProject.mave.domain.Answer;
import com.maveProject.mave.domain.Group;
import com.maveProject.mave.domain.Member;
import com.maveProject.mave.domain.Question;
import com.maveProject.mave.repository.AnswerRepository;
import com.maveProject.mave.repository.GroupRepository;
import com.maveProject.mave.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;


    @Transactional
    public void createAnswer(Member member, Question question, String content){
        Answer answer = new Answer(content);
        answer.addAnswer(question);
        answer.fromMember(member);
        answerRepository.save(answer);

    }




}
