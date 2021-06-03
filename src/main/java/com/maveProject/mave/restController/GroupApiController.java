package com.maveProject.mave.restController;


import com.maveProject.mave.domain.Flower;
import com.maveProject.mave.domain.Group;
import com.maveProject.mave.domain.IsFinish;
import com.maveProject.mave.domain.Member;
import com.maveProject.mave.service.GroupService;
import com.maveProject.mave.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;


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
        LocalDateTime questionTime = LocalDateTime.parse(request.getQuestionTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Group group = new Group(request.getGroupName(),questionTime,IsFinish.NO,Flower.ZERO,1l,1l); // 요청이 들어온 이름으로 그룹 생성
        groupService.saveGroup(group); // DB에 그룹 저장
        Member member = memberService.findMemberByName(request.getUserId()); // 그룹에 들어갈 멤버 찾아오기
        Long groupId = memberService.joinGroup(member, group); // 그룹 가입
        return new CreateGroupResponse(groupId,group.getDiaryDate());
    }

    /**
     * 기존에 있는 그룹에 가입하는 api 입니다.
     */
    @PostMapping("/api/groups/{groupId}")
    public JoinGroupResponse joinGroup(@PathVariable(value = "groupId") Long groupId,
                                       @RequestBody JoinGroupRequest request) {
        Member member = memberService.findMemberByName(request.getUserId()); // 그룹에 들어갈 멤버 찾아오기
        Group group = groupService.findGroup(groupId); // 들어갈 그룹 찾아오기
        Long joinGroupId = memberService.joinGroup(member, group); // 그룹 가입
        groupService.setCount(group);
        groupService.changeIsFinish(group);
        return new JoinGroupResponse(joinGroupId);
    }

    /**
     * 자신이 속한 그룹을 찾는 api 입니다.
     */
    @PostMapping("/api/findGroups")
    public FindGroupResponse findGroup(@RequestBody JoinGroupRequest request) {
        Member member = memberService.findMemberByName(request.getUserId());
        Group group = groupService.findGroup(member.getGroup().getId());
        Boolean isDateChanged = groupService.compareDate(group);
        group.flowerStatusCheck();
        String format = group.getQuestionTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return new FindGroupResponse(group.getId(),group.getGroupName(),group.getFlowerCount(),group.getFlower().flowerStatus(),format,group.getDiaryDate(),group.getCompleteDate(),isDateChanged);
    }

    //====== DTO ======//

    @Data
    static class JoinGroupRequest {
        private String userId;

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
        private String userId;
        private String groupName;
        private String questionTime;
    }


    @Data
    static class CreateGroupResponse {

        private Long groupId;
        private Long diaryDate;

        public CreateGroupResponse(Long groupId, Long diaryDate) {
            this.groupId = groupId;
            this.diaryDate = diaryDate;
        }
    }

    @Data
    static class FindGroupResponse{
        private Long groupId;
        private String groupName;
        private int flowerCount;
        private int flowerStatus;
        private String questionTime;
        private Long diaryDate;
        private Long completeDate;
        private Boolean isDateChanged;


        public FindGroupResponse(Long groupId, String groupName, int flowerCount, int flowerStatus, String questionTime, Long diaryDate,Long completeDate, Boolean isDateChanged) {
            this.groupId = groupId;
            this.groupName = groupName;
            this.flowerCount = flowerCount;
            this.flowerStatus = flowerStatus;
            this.questionTime = questionTime;
            this.diaryDate = diaryDate;
            this.completeDate = completeDate;
            this.isDateChanged = isDateChanged;

        }
    }




}
