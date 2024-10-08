package com.EducationSystem.Enrollement.Controllers;

import com.EducationSystem.Enrollement.DTO.AuthResponseDTO;
import com.EducationSystem.Enrollement.DTO.LoginDTO;
import com.EducationSystem.Enrollement.DTO.RegisterDTO;
import com.EducationSystem.Enrollement.Model.Role;
import com.EducationSystem.Enrollement.Model.UserEnitity;
import com.EducationSystem.Enrollement.Repository.RoleRepository;
import com.EducationSystem.Enrollement.Repository.UserRepository;
import com.EducationSystem.Enrollement.Security.JWTGenerator;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;


    @Autowired

    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder ,JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator=jwtGenerator;

    }
    @PostMapping("/login")

    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername()
                ,loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token),HttpStatus.OK);


    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        if (userRepository.existsByUsername(registerDTO.getUsername())){
            return new ResponseEntity<>("UserName Exists", HttpStatus.BAD_REQUEST);

        }
        UserEnitity user=new UserEnitity();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode((registerDTO.getPassword())));
        Role roles=roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));
        userRepository.save(user);
        return new ResponseEntity<>("User Created Sucessfully",HttpStatus.OK);





    }
}
