package com.maveProject.mave.restController;

import com.maveProject.mave.domain.Member;
import com.maveProject.mave.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService memberService;


    @PostMapping("/api/members")
    public JoinMemberResponse saveMember(@RequestBody JoinMemberRequest request) {
        Member member = new Member(request.getUserId());
        Long joinedMemberId = memberService.joinMember(member);
        return new JoinMemberResponse(joinedMemberId);
    }


    //====== DTO ======//


    @Data
    static class JoinMemberRequest {
        private String userId; // 어플에서 유저가 사용하는 ID값
    }

    @Data
    static class JoinMemberResponse {
        private Long id;    // DB에 저장된 user의 pk값

        public JoinMemberResponse(Long id) {
            this.id = id;
        }
    }


}
