package com.start.pager.usersOnBoarding.repository;


import com.start.pager.usersOnBoarding.model.User;
import com.start.pager.usersOnBoarding.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

//    Optional<UserProfile> findByUsername(String username);

    Optional<UserProfile> findByUser(User username);
}
