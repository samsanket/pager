package com.start.pager.usersOnBoarding.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.start.pager.usersOnBoarding.model.User}
 */
public class UserDto implements Serializable {
    private final Long id;
    @Size(max = 20)
    @NotBlank
    private final String username;
    @Size(max = 50)
    @Email
    @NotBlank
    private final String email;
    @Size(max = 120)
    @NotBlank
    private final String password;
    private final Boolean authenticated;
    private final Set<RoleDto> roles;

    public UserDto(Long id, String username, String email, String password, Boolean authenticated, Set<RoleDto> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authenticated = authenticated;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto entity = (UserDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.username, entity.username) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.password, entity.password) &&
                Objects.equals(this.authenticated, entity.authenticated) &&
                Objects.equals(this.roles, entity.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, authenticated, roles);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "username = " + username + ", " +
                "email = " + email + ", " +
                "password = " + password + ", " +
                "authenticated = " + authenticated + ", " +
                "roles = " + roles + ")";
    }
}