package api.petparent.application.core.service;

import api.petparent.application.core.dto.PetDTO;
import api.petparent.infraestructure.web.requests.AddPetRequestModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public interface PetService {
    ResponseEntity<String> addPet(String userId, AddPetRequestModel addPetRequestModel) throws ExecutionException, InterruptedException;
    ResponseEntity<List<PetDTO>> listPets(String userId) throws ExecutionException, InterruptedException;
    ResponseEntity<String> getPet(String userId, String petId);
    ResponseEntity<String> deletePet(String petId);
}
