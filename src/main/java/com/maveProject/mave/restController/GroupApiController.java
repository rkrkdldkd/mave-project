package com.maveProject.mave.restController;


import com.maveProject.mave.domain.Group;
import com.maveProject.mave.domain.Member;
import com.maveProject.mave.service.GroupService;
import com.maveProject.mave.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class GroupApiController {

    private final MemberService memberService;
    private final GroupService groupService;


    /**
     * 그룹을 생성한 후 DB에 저장하는 그룹 생성 api 입니다.
     */
    @PostMapping("/api/groups")
    public CreateGroupResponse createGroup(@RequestBody CreateGroupRequest request) {
        Group group = new Group(request.getGroupName()); // 요청이 들어온 이름으로 그룹 생성
        groupService.saveGroup(group); // DB에 그룹 저장
        Member member = memberService.findMember(request.getUserId()); // 그룹에 들어갈 멤버 찾아오기
        Long groupId = memberService.joinGroup(member, group); // 그룹 가입
        return new CreateGroupResponse(groupId);
    }

    /**
     * 기존에 있는 그룹에 가입하는 api 입니다.
     */
    @PostMapping("/api/groups/{groupId}")
    public JoinGroupResponse joinGroup(@PathVariable(value = "groupId") Long groupId,
                                       @RequestBody JoinGroupRequest request) {
        Member member = memberService.findMember(request.getUserId()); // 그룹에 들어갈 멤버 찾아오기
        Group group = groupService.findGroup(groupId); // 들어갈 그룹 찾아오기
        Long joinGroupId = memberService.joinGroup(member,group); // 그룹 가입
        return new JoinGroupResponse(joinGroupId);

    }


    //====== DTO ======//

    @Data
    static class JoinGroupRequest {
        private Long userId;

    }

    @Data
    static class JoinGroupResponse {
        private Long groupId;

        public JoinGroupResponse(Long groupId) {
            this.groupId = groupId;
        }
    }


    @Data
    static class CreateGroupRequest {
        private Long userId;
        private String groupName;
    }


    @Data
    static class CreateGroupResponse {

        private Long groupId;

        public CreateGroupResponse(Long groupId) {
            this.groupId = groupId;
        }


    }


}
