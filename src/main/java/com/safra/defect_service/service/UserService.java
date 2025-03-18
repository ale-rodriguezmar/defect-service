package com.safra.defect_service.service;

import com.safra.defect_service.dto.AuthenticationRequest;
import com.safra.defect_service.dto.AuthenticationResponse;
import com.safra.defect_service.entity.User;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserService {
    User register(String username, String rawPassword, Long roleId, String mail, Long areaId);

    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}
