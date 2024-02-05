package com.vms.server.admin.controller;

import com.vms.server.admin.service.LoginService;
import com.vms.server.domain.dto.CommonCodeDto;
import com.vms.server.domain.dto.LoginDto;
import com.vms.server.domain.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<UserInfoDto> login(@RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) throws Exception{
        return ResponseEntity.ok(loginService.findByUserId(loginDto,request,response));
    }

    @GetMapping("/login/common-plant")
    public ResponseEntity<List<CommonCodeDto>> getPlantCode(@RequestParam String userId) {
        return ResponseEntity.ok(loginService.getPlantCode(userId));
    }

}
