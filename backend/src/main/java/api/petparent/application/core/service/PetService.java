package api.petparent.application.core.service;

import api.petparent.infraestructure.web.requests.AddPetRequestModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public interface PetService {
    ResponseEntity<String> addPet(String userId, AddPetRequestModel addPetRequestModel) throws ExecutionException, InterruptedException;
    ResponseEntity<String> listPets(String userId);
    ResponseEntity<String> getPet(String userId, String petId);
    ResponseEntity<String> deletePet(String petId);
}
