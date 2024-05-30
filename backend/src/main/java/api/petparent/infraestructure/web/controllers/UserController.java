package api.petparent.infraestructure.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("petparent/api/v1/user")
public class UserController {

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {

            return new ResponseEntity<>("Invalid request, null parameters", HttpStatus.BAD_REQUEST);
        }
    }