package com.vms.server.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vms.server.domain.entity.adm.AdmUserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
public class CustomUserDetails implements UserDetails {

    private String id;

    private String name;

    private String plant;

    @JsonIgnore
    private String password;

    private String languageType;
    private Collection<? extends GrantedAuthority> authorities;

    private AdmUserInfo member;

    public CustomUserDetails(AdmUserInfo member){
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(member.getRoleId()));
        this.id = member.getUserId();
        this.name = member.getUserName();
        this.plant = member.getPlant();
        this.password = member.getPassword();
        this.languageType =member.getLanguageType();
        this.authorities = authorityList;
        this.member = member;
    }

    public String getLanguageType() {
        return languageType;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getId(){
        return this.id;
    }

    public String getPlant() { return this.plant; }

    public AdmUserInfo getMember() { return this.member; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
