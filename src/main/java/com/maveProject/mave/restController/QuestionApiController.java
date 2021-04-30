package com.maveProject.mave.restController;

import com.maveProject.mave.domain.QuestionBank;
import com.maveProject.mave.service.GroupService;
import com.maveProject.mave.service.QuestionBankService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QuestionApiController {

    private final QuestionBankService questionBankService;
    private final GroupService groupService;

    @PostMapping("/api/question/{questionNumber}")
    public giveQuestionResponse giveQuestion(@PathVariable(value = "questionNumber") Long questionNumber){
        QuestionBank question = questionBankService.findQuestion(questionNumber);

        return new giveQuestionResponse(question.getContent());
    }


    //===== DTO =====/

    @Data
    static class giveQuestionRequest{

    }

    @Data
    static class giveQuestionResponse{
        private String questionContent;

        public giveQuestionResponse(String questionContent) {
            this.questionContent = questionContent;
        }
    }
}
