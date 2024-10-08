package com.EducationSystem.Enrollement.Security;

import com.EducationSystem.Enrollement.Model.Role;
import com.EducationSystem.Enrollement.Model.UserEnitity;
import com.EducationSystem.Enrollement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class CustomUserDetailsServices implements UserDetailsService {
    private UserRepository userRepository;
    @Autowired

    public CustomUserDetailsServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEnitity user=userRepository.findByUsername( username).orElseThrow(()->new UsernameNotFoundException("user not found"));

        return new User(user.getUsername(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));

    }
    private Collection<GrantedAuthority>mapRolesToAuthorities(List<Role>roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
