package api.petparent.controllers;

import api.petparent.requests.user.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import api.petparent.services.LoginService;

@RestController
@RequestMapping("/api/v1/petparent")
public class UserController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    @RequestMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        if (loginRequest != null) {
            loginService.login(loginRequest);
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Null request", HttpStatus.BAD_REQUEST);

        }
    }
}