package com.maveProject.mave.restController;

import com.maveProject.mave.domain.CustomQuestion;
import com.maveProject.mave.domain.Group;
import com.maveProject.mave.domain.Question;
import com.maveProject.mave.repository.CustomRepository;
import com.maveProject.mave.service.GroupService;
import com.maveProject.mave.service.QuestionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CustomApiController {

    private final CustomRepository customRepository;
    private final GroupService groupService;
    private final QuestionService questionService;

    @PostMapping("/api/custom")
    public createCustomQuestionResponse createCustomQuestion(@RequestBody createCustomQuestionRequest request){
        CustomQuestion customQuestion = new CustomQuestion(request.getQuestionContent(), request.getDiaryDate(),request.getGroupId());
        customRepository.save(customQuestion);
        return new createCustomQuestionResponse("questionNumber");
    }

    @PostMapping("/api/custom/search")
    public checkCustomResponse checkCustom(@RequestBody checkCustomRequest request){
        String customQuestion = customRepository.findCustomQuestion(request.groupId, request.diaryDate);
        return new checkCustomResponse(customQuestion);
    }

    @PostMapping("/api/custom/{questionNumber}")
    public UseCustomQuestionResponse useCustomQuestion(@PathVariable(value = "questionNumber") Long questionNumber,
                                  @RequestBody UseCustomQuestionRequest request){
        String customQuestion = customRepository.findCustomQuestion(request.groupId, questionNumber);
        Group group = groupService.findGroup(request.groupId);
        Question todayQuestion = new Question(group, customQuestion, questionNumber + 1);
        questionService.createQuestion(todayQuestion);
        groupService.setCompleteDate(group,questionNumber);
        groupService.setCount(group);
        groupService.changeIsFinish(group);
        return new UseCustomQuestionResponse(customQuestion);

    }

    @Data
    static class createCustomQuestionRequest{
        private Long groupId;
        private Long diaryDate;
        private String questionContent;
    }

    @Data
    static class createCustomQuestionResponse{
        private String questionContent;

        public createCustomQuestionResponse(String questionContent) {
            this.questionContent = questionContent;
        }
    }

    @Data
    static class UseCustomQuestionRequest{
        private Long groupId;

    }

    @Data
    static class UseCustomQuestionResponse{
        private String questionContent;

        public UseCustomQuestionResponse(String questionContent) {
            this.questionContent = questionContent;
        }
    }

    @Data
    static class checkCustomRequest{
        private Long groupId;
        private Long diaryDate;
    }

    @Data
    static class checkCustomResponse{
        private String questionContent;

        public checkCustomResponse(String questionContent) {
            this.questionContent = questionContent;
        }
    }

}
