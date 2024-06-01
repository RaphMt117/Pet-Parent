package api.petparent.infraestructure.web.controllers;

import api.petparent.application.core.dto.PetDTO;
import api.petparent.application.core.service.PetService;
import api.petparent.infraestructure.web.requests.AddPetRequestModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping("petparent/api/v1/pet")
public class PetController {

    @Autowired
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> addPet(@PathVariable String userId, @RequestBody AddPetRequestModel addPetRequestModel) throws ExecutionException, InterruptedException {

            var response = petService.addPet(userId, addPetRequestModel);

            return response;
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<PetDTO>> listPets(@PathVariable String userId) {
        try {
            var response = petService.listPets(userId);
            log.info("Listing pets from: {}", userId);

            return response;
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}/{petId}")
    public ResponseEntity<String> getPet(@PathVariable String userId, @PathVariable String petId) {
        try {
            petService.getPet(userId, petId);
            log.info("Getting pet: {}, from user: {}", petId, userId);
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
