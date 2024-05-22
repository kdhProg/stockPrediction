package com.example.stockpredict.controller.user;

import com.example.stockpredict.config.security.UserPrincipal;
import com.example.stockpredict.request.user.UserJoinRequest;
import com.example.stockpredict.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    /*
     * 로그인, 로그아웃 => SecurityConfig
     * */

    /* 회원가입 */
    @PostMapping("/join")
    public void join(@RequestBody @Valid UserJoinRequest userJoinRequest){
        userService.userJoin(userJoinRequest);
    }

    /* 현재 로그인 사용자 정보(아이디)*/
    @GetMapping("/currentUserAccount")
    public String getUserInfo(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return userPrincipal.getUserAccount();
    }

    /* 현재 로그인 사용자 구독여부  무료:0  유료:1 */
    @GetMapping("/currentUserPlan")
    public Integer currentUserPlan(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return userService.getCurrentUserPlan(userPrincipal);
    }


}
