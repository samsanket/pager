package com.start.pager.post;

import com.start.pager.annotation.CurrentUser;
import com.start.pager.annotation.CustomUserDetails;
import com.start.pager.usersOnBoarding.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {


    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @GetMapping("/")
    public String getUser(@AuthenticationPrincipal UserDetails userDetails){
                    log.info("inside getUser method");
                    log.info(userDetails.toString());
            return userDetails.getUsername();
    }
}
