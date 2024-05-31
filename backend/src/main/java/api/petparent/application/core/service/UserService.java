package api.petparent.application.core.service;

import api.petparent.infraestructure.web.requests.user.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public interface UserService {
    ResponseEntity<String> addUser(LoginRequest loginRequest) throws ExecutionException, InterruptedException;

    ResponseEntity<String> deleteUser(String email);

    ResponseEntity<String> loginUser(LoginRequest loginRequest);
}
