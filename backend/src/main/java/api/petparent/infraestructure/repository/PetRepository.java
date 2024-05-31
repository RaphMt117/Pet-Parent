package api.petparent.infraestructure.repository;

import api.petparent.infraestructure.web.requests.pet.AddPetRequestModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;

@Slf4j
@Repository
public class PetRepository {
    public ResponseEntity<String> addPet(AddPetRequestModel addPetRequestModel) throws ExecutionException, InterruptedException {
        CollectionReference db = FirestoreClient.getFirestore().collection("pet_parents");

        String userId = addPetRequestModel.getUserId();
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

        addPetRequestModel.setUserId(null);
        var newPet = db.document(userId).collection("pets").add(addPetRequestModel);

        return new ResponseEntity<>("Pet added with ID: " + newPet.get().getId(), HttpStatus.OK);
    }
}
