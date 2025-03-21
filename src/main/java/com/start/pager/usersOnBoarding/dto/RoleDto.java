package com.start.pager.usersOnBoarding.dto;

import com.start.pager.usersOnBoarding.model.ERole;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.start.pager.usersOnBoarding.model.Role}
 */
public class RoleDto implements Serializable {
    private final Integer id;
    private final ERole name;

    public RoleDto(Integer id, ERole name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public ERole getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto entity = (RoleDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ")";
    }
}