package com.vms.server.domain.dto;

import com.vms.server.domain.entity.adm.AdmUserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {
    private String authToken;
    private UserInfo userInfo;
    private List<CommonMenuDto> menuList;

    @Getter
    public static class UserInfo {
        private String plant;
        private String userId;
        private String userName;
        private String languageType;
        private String department;
        private String departmentName;
        private String departmentAs;
        private String email;
        private String phoneNumber;
        private String roleId;
        private Boolean superUser;
        private List<String> myDeviceList;


        public UserInfo(AdmUserInfo userInfo) {
            this.plant = userInfo.getPlant();
            this.userId = userInfo.getUserId();
            this.userName = userInfo.getUserName();
            this.languageType = setLangType(userInfo.getLanguageType() );
            this.email = userInfo.getEmailId();
            this.phoneNumber = userInfo.getPhoneNo();
            this.roleId = userInfo.getRoleId();
            this.department = userInfo.getDepartment();
            this.superUser = convertSuperUserValue(userInfo.getSuperUser());
        }

        private Boolean convertSuperUserValue (String supperUserYn) {
            if(supperUserYn == null) return false;
            else if("Y".equals(supperUserYn.toUpperCase())) return true;
            else return false;
        }

        private String setLangType(String languageType) {
            switch (languageType){
                case "K":
                    return "ko";
                case"C":
                    return "cn";
                default:
                    return "en";
            }
        }

        public void findMyDeviceList(List<String> myDeviceList){
            this.myDeviceList = myDeviceList;
        }
    }
}
