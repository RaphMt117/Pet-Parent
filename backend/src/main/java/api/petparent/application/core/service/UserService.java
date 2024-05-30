package api.petparent.application.core.service;

import api.petparent.infraestructure.web.requests.user.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<String> addUser(LoginRequest loginRequest);
}
