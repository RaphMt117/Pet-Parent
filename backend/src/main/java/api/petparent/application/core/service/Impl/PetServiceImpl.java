package api.petparent.application.core.service.Impl;

import api.petparent.application.core.dto.PetDTO;
import api.petparent.application.core.service.PetService;
import api.petparent.infraestructure.repository.PetRepository;
import api.petparent.infraestructure.web.requests.AddPetRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    @Autowired
    private final PetRepository petRepository;

    @Override
    public ResponseEntity<String> addPet(String userId, AddPetRequestModel addPetRequestModel) throws ExecutionException, InterruptedException {

        var response = petRepository.addPet(userId, addPetRequestModel);

        return response;
    }

    @Override
    public ResponseEntity<List<PetDTO>> listPets(String userId) throws ExecutionException, InterruptedException {

        var response = petRepository.listPets(userId);

        return response;
    }

    @Override
    public ResponseEntity<String> getPet(String userId, String petId) {
        return null;
    }

    @Override
    public ResponseEntity<String> deletePet(String userId, String petId) throws ExecutionException, InterruptedException {

        var response = petRepository.deletePet(userId, petId);

        return response;
    }
}
