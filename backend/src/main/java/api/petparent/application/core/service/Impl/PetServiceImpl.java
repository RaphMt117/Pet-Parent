package api.petparent.application.core.service.Impl;

import api.petparent.application.core.service.PetService;
import api.petparent.infraestructure.web.requests.pet.AddPetRequestModel;
import org.springframework.http.ResponseEntity;

public class PetServiceImpl implements PetService {
    @Override
    public ResponseEntity<String> addPet(AddPetRequestModel addPetRequestModel) {
        return null;
    }

    @Override
    public ResponseEntity<String> listPets(String userId) {
        return null;
    }

    @Override
    public ResponseEntity<String> getPet(String petId) {
        return null;
    }

    @Override
    public ResponseEntity<String> deletePet(String petId) {
        return null;
    }
}
