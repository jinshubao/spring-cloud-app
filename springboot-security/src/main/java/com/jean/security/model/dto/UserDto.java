package com.jean.security.model.dto;

import com.jean.security.model.SysUser;

import java.io.Serializable;
import java.util.Date;

public class UserDto  implements Serializable {

    public UserDto() {
    }

    public UserDto(SysUser user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createTime = user.getCreateTime();
    }

    private String username;

    private String email;

    private Date createTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
