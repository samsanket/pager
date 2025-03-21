package com.start.pager.usersOnBoarding.service;


import com.start.pager.response.ResponseCommon;
import com.start.pager.response.ResponseSBuilder;
import com.start.pager.usersOnBoarding.dto.UserSinginRquest;
import com.start.pager.usersOnBoarding.model.User;
import com.start.pager.usersOnBoarding.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserOnBoardingService {

    @Autowired
    ResponseSBuilder responseSBuilder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResponseCommon singin(UserSinginRquest userSinginRquest) {
        User user= new User();
        user.setAuthenticated(false);
        user.setEmail(userSinginRquest.getEmail());
        user.setPassword(userSinginRquest.getPassword());
        user.setUsername(userSinginRquest.getUserName());
        userRepository.save(user);
    return responseSBuilder.createResponse("User signed in successfully");
    }

    public ResponseCommon singup(UserSinginRquest userSinginRquest) {


        return responseSBuilder.createResponse("User signed in successfully");
    }


}
