package com.maveProject.mave.restController.joinMemberDto;

import lombok.Data;

@Data
public class JoinMemberResponse {
    private Long id;    // DB에 저장된 user의 pk값

    public JoinMemberResponse(Long id) {
        this.id = id;
    }
}
