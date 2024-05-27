package api.petparent.services;

import api.petparent.requests.user.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    ResponseEntity<String> addUser(LoginRequest loginRequest);
}
