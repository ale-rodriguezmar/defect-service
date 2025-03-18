package com.safra.defect_service.service.Impl;

import com.safra.defect_service.dto.AuthenticationRequest;
import com.safra.defect_service.dto.AuthenticationResponse;
import com.safra.defect_service.entity.Area;
import com.safra.defect_service.entity.Role;
import com.safra.defect_service.entity.User;
import com.safra.defect_service.repository.AreaRepository;
import com.safra.defect_service.repository.RoleRepository;
import com.safra.defect_service.repository.UserRepository;
import com.safra.defect_service.service.JwtService;
import com.safra.defect_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;


    @Override
    public User register(String username, String rawPassword, Long roleId, String mail, Long areaId) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setEmail(mail);
        Area area = areaRepository.findById(areaId).orElseThrow(() -> new RuntimeException("Role not found"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        user.setArea(area);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        return userRepository.save(user);
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String jwtToken = jwtService.generateToken(userDetails);

        User user = userRepository.findByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new AuthenticationResponse(jwtToken,user.getUsername(),user.getId(),user.getRoles().stream().map(Role::getId).collect(Collectors.toSet()));
    }

}


