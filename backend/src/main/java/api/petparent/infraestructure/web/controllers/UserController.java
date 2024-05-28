package api.petparent.infraestructure.web.controllers;

import api.petparent.infraestructure.web.requests.user.LoginRequest;
import api.petparent.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("petparent/api/v1/users")
public class UserController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody LoginRequest loginRequest) {
        if (loginRequest != null) {
            ResponseEntity<String> responseEntity = loginService.addUser(loginRequest);
            return new ResponseEntity<>(responseEntity.getBody(), responseEntity.getStatusCode());
        } else {
            return new ResponseEntity<>("Invalid request, null parameters", HttpStatus.BAD_REQUEST);

        }
    }
}