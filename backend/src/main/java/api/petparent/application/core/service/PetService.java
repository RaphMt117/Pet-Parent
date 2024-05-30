package api.petparent.application.core.service;

import api.petparent.infraestructure.web.requests.pet.AddPetRequestModel;
import org.springframework.http.ResponseEntity;

public interface PetService {
    ResponseEntity<String> addPet(AddPetRequestModel addPetRequestModel);
    ResponseEntity<String> listPets(String userId);
    ResponseEntity<String> getPet(String petId);
    ResponseEntity<String> deletePet(String petId);
}
