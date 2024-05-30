package api.petparent.infraestructure.web.controllers;

import api.petparent.infraestructure.web.requests.pet.AddPetRequestModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@Slf4j
@RestController
@RequestMapping("petparent/api/v1/pet")
public class PetController {

    @PostMapping
    public ResponseEntity<String> addPet(@RequestBody AddPetRequestModel addPetRequestModel) {
        try {
            log.info("Adding pet");
            return new ResponseEntity<>("Pet added", HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error adding pet", e);
            return new ResponseEntity<>("Error adding pet", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<String> listPets(@PathVariable String userId) {
        try {
            log.info("Listing pets from: {}", userId);

            return new ResponseEntity<>("Pets listed", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error listing pets", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{petId}")
    public ResponseEntity<String> getPet(@PathVariable String petId) {
        try {
            log.info("Getting pet: {}", petId);
            return new ResponseEntity<>("Pet retrieved", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error getting pet", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<String> deletePet(@PathVariable String petId) {
        try {
            log.info("Deleting pet: {}", petId);


            return new ResponseEntity<>("Pet deleted", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting pet", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
