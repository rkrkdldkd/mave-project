package com.maveProject.mave.restController;

import com.maveProject.mave.domain.Member;
import com.maveProject.mave.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AnswerApiController {

    private final MemberService memberService;


//    @PostMapping("/api/answer/{questionNumber}")
//    public RegistAnswerResponse registAnswer(@PathVariable(value = "questionNumber") Long questionNumber,
//                                             @RequestBody RegistAnswerRequest request){
//
//
//
//    }



    //==== DTO =====//

    @Data
    static class RegistAnswerRequest{

        private String userId;
        private String groupName;
        private String answer;

    }

    @Data
    static class RegistAnswerResponse{

    }
}
