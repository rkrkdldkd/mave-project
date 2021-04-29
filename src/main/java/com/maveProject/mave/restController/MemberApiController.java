package com.maveProject.mave.restController;

import com.maveProject.mave.domain.Member;
import com.maveProject.mave.restController.joinGroupDto.CreateGroupRequest;
import com.maveProject.mave.restController.joinGroupDto.CreateGroupResponse;
import com.maveProject.mave.restController.joinGroupDto.JoinGroupRequest;
import com.maveProject.mave.restController.joinGroupDto.JoinGroupResponse;
import com.maveProject.mave.restController.joinMemberDto.JoinMemberRequest;
import com.maveProject.mave.restController.joinMemberDto.JoinMemberResponse;
import com.maveProject.mave.service.GroupService;
import com.maveProject.mave.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService memberService;


    @PostMapping("/api/members")
    public JoinMemberResponse saveMember(@RequestBody JoinMemberRequest request){
        Member member = new Member(request.getUserId());
        Long joinedMemberId = memberService.joinMember(member);
        return new JoinMemberResponse(joinedMemberId);
    }

    @PostMapping("/api/groups")
    public CreateGroupResponse createGroup(@RequestBody CreateGroupRequest request){
        Long groupId = memberService.firstJoinGroup(request.getUserId());
        return new CreateGroupResponse(groupId);
    }

    @PostMapping("/api/groups/{groupId}")
    public JoinGroupResponse joinGroup(@PathVariable(value = "groupId") Long groupId,
                                       @RequestBody JoinGroupRequest request){
        Long joinGroupId = memberService.JoinGroup(request.getUserId(), groupId);
        return new JoinGroupResponse(joinGroupId);

    }





}
