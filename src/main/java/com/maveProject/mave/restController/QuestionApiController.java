package com.maveProject.mave.restController;

import com.maveProject.mave.domain.Group;
import com.maveProject.mave.domain.Question;
import com.maveProject.mave.domain.QuestionBank;
import com.maveProject.mave.service.GroupService;
import com.maveProject.mave.service.QuestionBankService;
import com.maveProject.mave.service.QuestionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class QuestionApiController {

    private final QuestionBankService questionBankService;
    private final QuestionService questionService;
    private final GroupService groupService;

    /**
     * 오늘의 질문을 보내줍니다.
     */
    @PostMapping("/api/question/{questionNumber}")
    public GiveQuestionResponse giveQuestion(
            @PathVariable(value = "questionNumber")
                    Long questionNumber,
            @RequestBody
                    GiveQuestionRequest request) {
        QuestionBank question = questionBankService.findQuestion(questionNumber);
        Group group = groupService.findGroup(request.groupId);
        Question todayQuestion = new Question(group, question.getContent(), question.getQuestionNumber());
        questionService.createQuestion(todayQuestion);
        groupService.setCompleteDate(group,questionNumber);
        groupService.setCount(group);
        groupService.changeIsFinish(group);
        return new GiveQuestionResponse(question.getContent());
    }


    /**
     * 지난 질문들을 List에 담아 반환해줍니다.
     */
    @PostMapping("/api/allQuestion/{questionNumber}")
    public List<GiveAllQuestionResponse> giveAllQuestion(@PathVariable(value = "questionNumber") Long questionNumber,
                                                         @RequestBody GiveAllQuestionRequest request) {
        Group group = groupService.findGroup(request.groupId);
        List<GiveAllQuestionResponse> result = questionService.findAllQuestion(group, questionNumber);
        return result;
    }


    //===== DTO =====/

    @Data
    static class GiveQuestionRequest {
        private Long groupId;

    }

    @Data
    static class GiveQuestionResponse {
        private String questionContent;

        public GiveQuestionResponse(String questionContent) {
            this.questionContent = questionContent;
        }
    }

    @Data
    static class GiveAllQuestionRequest {
        private Long groupId;

    }

    @Data
    public static class GiveAllQuestionResponse {
        private String questionContent;

        public GiveAllQuestionResponse(String questionContent) {
            this.questionContent = questionContent;
        }
    }
}
