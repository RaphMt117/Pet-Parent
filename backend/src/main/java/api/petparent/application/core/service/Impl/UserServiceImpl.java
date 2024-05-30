package api.petparent.application.core.service.Impl;

import api.petparent.application.core.service.UserService;
import api.petparent.application.core.dto.UserDTO;
import api.petparent.infraestructure.web.requests.user.LoginRequest;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public ResponseEntity<String> addUser(LoginRequest loginRequest) {

        UserDTO userDTO = new UserDTO();
        userDTO.setFullName(loginRequest.getFirstName() + " " + loginRequest.getLastName());
        userDTO.setFirstName(loginRequest.getFirstName());
        userDTO.setLastName(loginRequest.getLastName());
        userDTO.setPassword(loginRequest.getPassword());
        userDTO.setEmail(loginRequest.getEmail());

        log.info("UserDTO created: {}", userDTO);

        if (userDTO.getPassword().length() < 8) {
            return new ResponseEntity<>("Password must be at least 8 characters long", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("User created", HttpStatus.OK);
        }
    }
}
