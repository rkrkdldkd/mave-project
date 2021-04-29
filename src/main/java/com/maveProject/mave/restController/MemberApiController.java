package com.maveProject.mave.restController;

import com.maveProject.mave.domain.Member;
import com.maveProject.mave.restController.dto.CreateMemberRequest;
import com.maveProject.mave.restController.dto.CreateMemberResponse;
import com.maveProject.mave.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService memberService;


    @PostMapping("/api/members")
    public CreateMemberResponse saveMember(@RequestBody CreateMemberRequest request){
        Member member = new Member(request.getUserId());
        Long joinedMemberId = memberService.MemberJoin(member);
        return new CreateMemberResponse(joinedMemberId);
    }
}
