package api.petparent.services.Impl;

import api.petparent.dto.UserDTO;
import api.petparent.requests.user.LoginRequest;
import api.petparent.services.LoginService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class loginServiceImpl implements LoginService {

    @Override
    public ResponseEntity<String> login(LoginRequest loginRequest) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFullName(loginRequest.getFirstName() + " " + loginRequest.getLastName());
        userDTO.setFirstName(loginRequest.getFirstName());
        userDTO.setLastName(loginRequest.getLastName());
        userDTO.setEmail(loginRequest.getEmail());

        System.out.println(userDTO.toString());

        if (userDTO.getPassword().length() < 8) {
            return new ResponseEntity<>("Password must be at least 8 characters long", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Login sucessful", HttpStatus.OK);
        }
    }
}
