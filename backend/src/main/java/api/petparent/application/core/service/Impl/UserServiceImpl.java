package api.petparent.application.core.service.Impl;

import api.petparent.application.core.dto.UserDTO;
import api.petparent.application.core.service.UserService;
import api.petparent.infraestructure.repository.UserRepository;
import api.petparent.infraestructure.web.requests.LoginRequest;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<String> addUser(LoginRequest loginRequest) throws ExecutionException, InterruptedException {

        var response = userRepository.addUser(loginRequest);

        return response;
    }

    @Override
    public ResponseEntity<String> deleteUser(String email) throws ExecutionException, InterruptedException {

        var response = userRepository.deleteUser(email);

        return response;
    }

    @Override
    public ResponseEntity<UserDTO> getUser(String email) throws ExecutionException, InterruptedException {

        var response = userRepository.getUser(email);

        return response;
    }

    @Override
    public ResponseEntity<String> loginUser(LoginRequest loginRequest) {

        var response = userRepository.loginUser(loginRequest);

        return response;
    }
}
