package com.maveProject.mave.restController.joinGroupDto;

import lombok.Data;

@Data
public class CreateGroupResponse {

    private Long groupId;

    public CreateGroupResponse(Long groupId) {
        this.groupId = groupId;
    }


}
