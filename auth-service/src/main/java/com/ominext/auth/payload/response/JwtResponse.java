package com.ominext.auth.payload.response;

import lombok.Data;

@Data
public class JwtResponse {
    private Integer status;
    private String token;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
