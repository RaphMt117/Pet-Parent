package api.petparent.application.core.service;

import api.petparent.application.core.dto.UserDTO;
import api.petparent.infraestructure.web.requests.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public interface UserService {
    ResponseEntity<String> addUser(LoginRequest loginRequest) throws ExecutionException, InterruptedException;

    ResponseEntity<String> deleteUser(String email) throws ExecutionException, InterruptedException;

    ResponseEntity<UserDTO> getUser(String email) throws ExecutionException, InterruptedException;

    ResponseEntity<String> loginUser(LoginRequest loginRequest);
}
