package com.maveProject.mave.restController.joinGroupDto;

import lombok.Data;

@Data
public class JoinGroupResponse {
    private Long groupId;

    public JoinGroupResponse(Long groupId) {
        this.groupId = groupId;
    }
}
