package com.example.studentmanagement.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JWTAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Collection<? extends GrantedAuthority> roleList;

    public JWTAuthResponse(String accessToken, Collection<? extends GrantedAuthority> roleList) {
        this.accessToken = accessToken;
        this.roleList = roleList;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Collection<? extends GrantedAuthority> getRoleList() {
        return roleList;
    }

    public void setRoleList(Collection<? extends GrantedAuthority> roleList) {
        this.roleList = roleList;
    }
}
