package api.petparent.application.core.service.Impl;

import api.petparent.application.core.service.PetService;
import api.petparent.infraestructure.repository.PetRepository;
import api.petparent.infraestructure.web.requests.pet.AddPetRequestModel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    @Autowired
    private final PetRepository petRepository;

    @Override
    public ResponseEntity<String> addPet(AddPetRequestModel addPetRequestModel) throws ExecutionException, InterruptedException {

        var response = petRepository.addPet(addPetRequestModel);

        return response;
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
