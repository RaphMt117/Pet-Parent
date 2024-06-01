package api.petparent.infraestructure.repository;

import api.petparent.application.core.dto.PetDTO;
import api.petparent.application.core.dto.TaskDTO;
import api.petparent.infraestructure.web.requests.AddPetRequestModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Repository
public class PetRepository {
    public ResponseEntity<String> addPet(String userId, AddPetRequestModel addPetRequestModel) throws ExecutionException, InterruptedException {
        CollectionReference db = FirestoreClient.getFirestore().collection("pet_parents");

        log.info("Adding pet to user: " + userId);

        ApiFuture<DocumentSnapshot> user = db.document(userId).get();

        if (!user.get().exists()) {
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
        }

        if (addPetRequestModel.getPetName() == null || addPetRequestModel.getPetName().isEmpty()) {
            return new ResponseEntity<>("Pet name is required", HttpStatus.BAD_REQUEST);
        }

        ApiFuture<QuerySnapshot> future = db.document(userId).collection("pets").whereEqualTo("petName", addPetRequestModel.getPetName()).get();

        if(!future.get().isEmpty()){
            log.info("Pet already exists");
            return new ResponseEntity<>("Pet already exists", HttpStatus.CONFLICT);
        }

        var newPet = db.document(userId).collection("pets").add(addPetRequestModel);

        return new ResponseEntity<>("Pet added with ID: " + newPet.get().getId(), HttpStatus.OK);
    }

    public ResponseEntity<List<PetDTO>> listPets(String userId) throws ExecutionException, InterruptedException {
        CollectionReference db = FirestoreClient.getFirestore().collection("pet_parents");

        log.info("Listing pets to user:{} ", userId);

        ApiFuture<DocumentSnapshot> user = db.document(userId).get();

        if (!user.get().exists()) {
            throw new ExecutionException("User does not exist", null);
        }

        ApiFuture<QuerySnapshot> future = db.document(userId).collection("pets").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<PetDTO> pets = new ArrayList<>();
        if (documents.isEmpty()) {
            return new ResponseEntity<>(pets, HttpStatus.NOT_FOUND);
        }

        for (QueryDocumentSnapshot document : documents) {
            PetDTO pet = document.toObject(PetDTO.class);
            pet.setPetId(document.getId());
            pets.add(pet);
        }

        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    public ResponseEntity<String> deletePet(String userId, String petId) throws ExecutionException, InterruptedException {
        CollectionReference db = FirestoreClient.getFirestore().collection("pet_parents");

        log.info("Deleting pet: {}, from user: {}", petId, userId);

        ApiFuture<DocumentSnapshot> user = db.document(userId).get();
        ApiFuture<DocumentSnapshot> pet = db.document(userId).collection("pets").document(petId).get();

        if (!user.get().exists()) {
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
        }
        if (!pet.get().exists()) {
            return new ResponseEntity<>("Pet does not exist", HttpStatus.NOT_FOUND);
        }

        db.document(userId).collection("pets").document(petId).delete();

        return new ResponseEntity<>("Deleted pet with Id: " + petId, HttpStatus.OK);
    }
}
