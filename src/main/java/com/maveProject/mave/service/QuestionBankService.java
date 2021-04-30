package com.maveProject.mave.service;

import com.maveProject.mave.domain.QuestionBank;
import com.maveProject.mave.repository.QuestionBankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class QuestionBankService {

    private final QuestionBankRepository questionBankRepository;

    public QuestionBank findQuestion(Long questionNumber){
        List<QuestionBank> questions = questionBankRepository.findByNumber(questionNumber);
        return questions.get(0);
    }


}
