package com.maveProject.mave.restController;

import com.maveProject.mave.domain.Answer;
import com.maveProject.mave.domain.Group;
import com.maveProject.mave.domain.Member;
import com.maveProject.mave.domain.Question;
import com.maveProject.mave.service.AnswerService;
import com.maveProject.mave.service.GroupService;
import com.maveProject.mave.service.MemberService;
import com.maveProject.mave.service.QuestionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AnswerApiController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final GroupService groupService;
    private final MemberService memberService;

    /**
     * 어떤 그룹의 어떤 멤버가 어떤 질문에 대한 답을 했는지 등록합니다.
     */
    @PostMapping("/api/answer/{questionNumber}")
    public RegistAnswerResponse registAnswer(@PathVariable(value = "questionNumber") Long questionNumber,
                                             @RequestBody RegistAnswerRequest request){
        Member member = memberService.findMember(request.getUserId());
        Group group = groupService.findGroup(request.getGroupId());
        Question question = questionService.findQuestion(group,questionNumber);
        Answer answer = new Answer(request.getAnswer());
        answerService.createAnswer(member,group,question,answer);
        groupService.minusCount(group);
        Boolean state = groupService.compareState(group);
        return new RegistAnswerResponse(state ,"답변이 등록되었습니다.");

    }

    /**
     * 모두 답변을 했을 때, 해당 질문에 대한 답변을 List에 담아 반환합니다.
     */
    @GetMapping("/api/allAnswer/{questionNumber}")
    public List<AllAnswerResponse> allAnswer(@PathVariable(value = "questionNumber") Long questionNumber,
                                       @RequestBody AllAnswerRequest request){
        List<AllAnswerResponse> result = answerService.findAllAnswer(request.getGroupId(), questionNumber);

        return result;
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
        private Boolean isFinish;

        public RegistAnswerResponse(Boolean isFinish, String answer) {
            this.isFinish = isFinish;
            this.answer = answer;
        }
    }

    @Data
    static class AllAnswerRequest{
        private Long groupId;

    }

    @Data
    public static class AllAnswerResponse{
        private Long userId;
        private String answerContent;

        public AllAnswerResponse(Long userId, String answerContent) {
            this.userId = userId;
            this.answerContent = answerContent;
        }
    }


}
