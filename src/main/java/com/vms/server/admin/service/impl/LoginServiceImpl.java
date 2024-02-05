package com.vms.server.admin.service.impl;

import com.vms.server.admin.service.LoginService;
import com.vms.server.domain.dto.CommonCodeDto;
import com.vms.server.domain.dto.LoginDto;
import com.vms.server.domain.dto.UserInfoDto;
import com.vms.server.domain.entity.adm.AdmUserInfo;
import com.vms.server.qms.repository.jpa.AdmUserInfoRepository;
import com.vms.server.security.AuthUtil;
import com.vms.server.security.CustomUserDetails;
import com.vms.server.security.JwtAuthProvider;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {


    private final AdmUserInfoRepository admUserInfoRepository;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtAuthProvider jwtAuthProvider;
    private final AuthUtil authUtil;

    @Override
    public UserInfoDto findByUserId(LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UsernamePasswordAuthenticationToken   usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken( loginDto.getUserId() + ":" + loginDto.getPlant() , loginDto.getPassword() );


        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        CustomUserDetails accountContext = (CustomUserDetails) authentication.getPrincipal();
        accountContext.setPlant(loginDto.getPlant());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AdmUserInfo member = accountContext.getMember();

        String jwt = jwtAuthProvider.createToken(authentication);
        String authToken = "Bearer " + jwt;

        jwtAuthProvider.createRefreshToken( request, response, member );

        return null;
    }

    @Override
    public List<CommonCodeDto> getPlantCode(String userId) {
        return admUserInfoRepository.findByUserId(userId)
                .stream().map(CommonCodeDto::new).collect(Collectors.toList());
    }
}
