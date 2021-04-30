package com.maveProject.mave.restController;

import com.maveProject.mave.domain.Member;
import com.maveProject.mave.domain.Question;
import com.maveProject.mave.service.AnswerService;
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


    @PostMapping("/api/answer/{questionNumber}")
    public RegistAnswerResponse registAnswer(@PathVariable(value = "questionNumber") Long questionNumber,
                                             @RequestBody RegistAnswerRequest request){
        Question question = questionService.findQuestion(request.groupName, questionNumber);
        Member member = memberService.findMember(request.getUserId());
        answerService.createAnswer(member,question,request.getAnswer());
        return new RegistAnswerResponse("답변이 등록되었습니다.");


    }



    //==== DTO =====//

    @Data
    static class RegistAnswerRequest{

        private String userId;
        private String groupName;
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
