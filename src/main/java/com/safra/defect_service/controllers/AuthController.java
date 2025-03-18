package com.safra.defect_service.controllers;


import com.safra.defect_service.dto.AuthenticationRequest;
import com.safra.defect_service.dto.AuthenticationResponse;
import com.safra.defect_service.entity.User;
import com.safra.defect_service.service.JwtService;
import com.safra.defect_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        try {
            return ResponseEntity.ok(userService.login(request));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestParam String username,
                                           @RequestParam String password,
                                           @RequestParam Long  roleId,
                                           @RequestParam String mail,
                                           @RequestParam Long areaId) {
        User user = userService.register(username, password, roleId, mail,  areaId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        try {
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            boolean isValid = jwtService.validate(token);

            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setJwtToken(token);

            if (isValid) {
                return ResponseEntity.ok().body(authenticationResponse);
            } else {
                return ResponseEntity.badRequest().body("Token inválido");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al validar token: " + e.getMessage());
        }
    }
}