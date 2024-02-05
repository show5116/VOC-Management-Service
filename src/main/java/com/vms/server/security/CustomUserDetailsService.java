package com.vms.server.security;

import com.vms.server.admin.repository.jpa.AdminAdmEmployeeRepository;
import com.vms.server.domain.entity.adm.AdmUserInfo;
import com.vms.server.qms.repository.jpa.AdmUserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminAdmEmployeeRepository admEmployeeRepository;
    private final AdmUserInfoRepository admUserInfoRepository;
    private final ModelMapper modelMapper;
    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] inputs = username.split(":");

//        AdmUserInfo user = admUserInfoRepository.findByUserIdAndPlantAndAdmission(inputs[0], inputs[1],"Y").orElseThrow(
//                () -> new UsernameNotFoundException("User not found with username : " + username)
//        );
        AdmUserInfo user = admUserInfoRepository.findByUserId(inputs[0]).get(0);

        return new CustomUserDetails(user);
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsernameAndPlant(AuthToken authToken) throws UsernameNotFoundException {

        AdmUserInfo member = admUserInfoRepository.findByUserIdAndPlant(authToken.getId(), authToken.getPlant()).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username and plant : " + authToken.getId() + " , " + authToken.getPlant())
        );

        return new CustomUserDetails(member);
    }


}
