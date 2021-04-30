package com.maveProject.mave.restController;


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


    @PostMapping("/api/groups")
    public CreateGroupResponse createGroup(@RequestBody CreateGroupRequest request) {
        Long groupId = memberService.firstJoinGroup(request.getUserId(),request.getGroupName());
        return new CreateGroupResponse(groupId);
    }

    @PostMapping("/api/groups/{groupId}")
    public JoinGroupResponse joinGroup(@PathVariable(value = "groupId") Long groupId,
                                       @RequestBody JoinGroupRequest request) {
        Long joinGroupId = memberService.JoinGroup(request.getUserId(), groupId);
        return new JoinGroupResponse(joinGroupId);

    }


    //====== DTO ======//

    @Data
    static class JoinGroupResponse {
        private Long groupId;

        public JoinGroupResponse(Long groupId) {
            this.groupId = groupId;
        }
    }

    @Data
    static class JoinGroupRequest {
        private Long userId;

    }


    @Data
    static class CreateGroupResponse {

        private Long groupId;

        public CreateGroupResponse(Long groupId) {
            this.groupId = groupId;
        }


    }


    @Data
    static class CreateGroupRequest {
        private Long userId;
        private String groupName;
    }


}
