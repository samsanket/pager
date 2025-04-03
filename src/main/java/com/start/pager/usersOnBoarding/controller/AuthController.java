package com.start.pager.usersOnBoarding.controller;


import com.start.pager.response.JwtResponse;
import com.start.pager.response.MessageResponse;
import com.start.pager.security.jwt.JwtUtils;
import com.start.pager.usersOnBoarding.dto.LoginRequest;
import com.start.pager.usersOnBoarding.dto.SignupRequest;
import com.start.pager.usersOnBoarding.model.ERole;
import com.start.pager.usersOnBoarding.model.Role;
import com.start.pager.usersOnBoarding.model.User;
import com.start.pager.usersOnBoarding.repository.RoleRepository;
import com.start.pager.usersOnBoarding.repository.UserRepository;
import com.start.pager.usersOnBoarding.service.UserDetailsImpl;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;


    public void saveAudit(String apiname, String data) {
//        auditService.saveAudit(apiname, data);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        saveAudit("/api/auth/signin", "login a user with userName  " + loginRequest.getUsername());
        if (StringUtils.isBlank(loginRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username should not be blank"));
        }

        if (StringUtils.isBlank(loginRequest.getPassword())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Password should not be blank"));
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
        saveAudit("/api/auth/signin", "logged in  success " + userDetails.getUsername());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        saveAudit("/api/auth/signup", "register a user with user name " + signUpRequest.getUsername());

        if (StringUtils.isBlank(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username should not be blank"));
        }

        // Check if the email is blank
        if (StringUtils.isBlank(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email should not be blank"));
        }

        // Check if the role is blank or empty
        if (signUpRequest.getRole() == null || signUpRequest.getRole().isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Role should not be blank or empty"));
        }

        if (StringUtils.isBlank(signUpRequest.getPassword())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Password should not be blank"));
        }

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ROLE_ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        saveAudit("/api/auth/signin", "account created success " + user.getUsername());
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            return ResponseEntity.ok("Logout successful!");
        }
        return ResponseEntity.badRequest().body("You are not logged in!");
    }
}