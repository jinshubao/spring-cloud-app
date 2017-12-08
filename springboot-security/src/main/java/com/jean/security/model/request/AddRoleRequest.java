package com.jean.security.model.request;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * TODO
 *
 * @author jinshubao
 * @since TODO
 */
public class AddRoleRequest {

    @NotBlank
    @Length(max = 255, message = "角色名最长255位")
    private String name;

    @Length(max = 255, message = "角色值最长255位")
    private String role;

    @Length(max = 500, message = "备注最长500位")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AddRoleRequest that = (AddRoleRequest) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        return role != null ? role.equals(that.role) : that.role == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
