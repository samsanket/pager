package com.start.pager.usersOnBoarding.repository;


import com.start.pager.usersOnBoarding.model.ERole;
import com.start.pager.usersOnBoarding.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);


}
