package com.maveProject.mave.restController;

import com.maveProject.mave.domain.Member;
import com.maveProject.mave.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService memberService;

    /**
     * 회원가입시 들어오는 정보를 DB에 저장하는 회원가입 api 입니다.
     */
    @PostMapping("/api/members")
    public JoinMemberResponse saveMember(@RequestBody JoinMemberRequest request) {
        Member member = new Member(request.getUserId(),request.getPassword(),request.getUserName());
        // 안드로이드에서 넘어온 회원 정보를 이용해 객체 생성
        member.changePasswordToHash();
        // 비밀번호를 해쉬코드로 변환한다.
        Long joinedMemberId = memberService.joinMember(member);
        // DB에 비밀번호를 저장한다.
        return new JoinMemberResponse(joinedMemberId);
    }

    @PostMapping("/api/members/login")
    public LoginResponse loginCheck(@RequestBody LoginRequest request){
        Member findMember = memberService.findMemberByName(request.getUserId());
        // DB에서 로그인을 시도하는 멤버를 가져온다
        Boolean isOk = memberService.passwordCheck(Integer.toString(request.getPassword().hashCode()), findMember.getPassword());
        // 요청으로 들어온 비밀번호와 DB에 있는 비밀번호가 일치하는지 확인한다.

        return new LoginResponse(isOk);
    }


    //====== DTO ======//


    @Data
    static class JoinMemberRequest {
        private String userId;   // 어플에서 유저가 사용하는 ID값
        private String password; // 어플에서 유저가 사용하는 비밀번호값
        private String userName; // 어플에서 유저가 사용하는 이름값
    }

    @Data
    static class JoinMemberResponse {
        private Long userId;    // DB에 저장된 user의 PK값

        public JoinMemberResponse(Long userId) {
            this.userId = userId;
        }
    }

    @Data
    static class LoginRequest {
        private String userId;
        private String password;
    }

    @Data
    static class LoginResponse {
        private Boolean isLoginOk;

        public LoginResponse(Boolean isLoginOk) {
            this.isLoginOk = isLoginOk;
        }
    }
}
