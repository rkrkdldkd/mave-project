package com.maveProject.mave.restController;

import com.maveProject.mave.domain.Group;
import com.maveProject.mave.domain.Question;
import com.maveProject.mave.service.AnswerService;
import com.maveProject.mave.service.GroupService;
import com.maveProject.mave.service.MemberService;
import com.maveProject.mave.service.QuestionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AnswerApiController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final MemberService memberService;
    private final GroupService groupService;


    @PostMapping("/api/answer/{questionNumber}")
    public RegistAnswerResponse registAnswer(@PathVariable(value = "questionNumber") Long questionNumber,
                                             @RequestBody RegistAnswerRequest request){
        Group group = groupService.findGroup(request.getGroupId());
        Question question = questionService.findQuestion(group.getId(), questionNumber);
        answerService.createAnswer(group,question,request.getAnswer());
        return new RegistAnswerResponse("답변이 등록되었습니다.");

    }



    //==== DTO =====//

    @Data
    static class RegistAnswerRequest{

        private Long userId;
        private Long groupId;
        private String answer;

    }

    @Data
    static class RegistAnswerResponse{
        private String answer;

        public RegistAnswerResponse(String answer) {
            this.answer = answer;
        }
    }
}
