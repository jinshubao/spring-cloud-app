package com.jean.security.model.request;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * TODO
 *
 * @author jinshubao
 * @since TODO
 */
public class AddUserRequest {

    @NotBlank
    @Length(max = 255, message = "用户名最长255位")
    private String name;

    @Length(max = 255, message = "姓名最长255位")
    private String realName;

    @NotBlank
    @Length(max = 255, message = "密码最长255位")
    private String password;

    @Email(message = "邮箱格式不正确")
    @Length(max = 255, message = "邮箱最长255位")
    private String email;

    @Length(max = 500, message = "备注最长500位")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
