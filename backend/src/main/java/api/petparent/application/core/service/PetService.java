package api.petparent.application.core.service;

import api.petparent.infraestructure.web.requests.pet.AddPetRequestModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public interface PetService {
    ResponseEntity<String> addPet(AddPetRequestModel addPetRequestModel) throws ExecutionException, InterruptedException;
    ResponseEntity<String> listPets(String userId);
    ResponseEntity<String> getPet(String petId);
    ResponseEntity<String> deletePet(String petId);
}
