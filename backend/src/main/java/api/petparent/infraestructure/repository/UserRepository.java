package api.petparent.infraestructure.repository;

import api.petparent.infraestructure.web.requests.user.LoginRequest;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;

@Slf4j
@Getter
@Setter
@Repository
public class UserRepository {

    public ResponseEntity<String> addUser(LoginRequest loginRequest) throws ExecutionException, InterruptedException {
        CollectionReference db = FirestoreClient.getFirestore().collection("Users");

        ApiFuture<QuerySnapshot> future = db.whereEqualTo("email", loginRequest.getEmail()).get();

        if (!future.get().isEmpty()) {
            log.info("User already exists");
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        }

        ApiFuture<DocumentReference> newUser = db.add(loginRequest);

        return new ResponseEntity<>("User added with ID: " + newUser.get().getId(), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteUser(String email) throws ExecutionException, InterruptedException {
        CollectionReference db = FirestoreClient.getFirestore().collection("Users");

        ApiFuture<QuerySnapshot> future = db.whereEqualTo("email", email).get();

        try {
            future.get().getDocuments().get(0);
        } catch (IndexOutOfBoundsException e) {
            log.info("User not found");
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        DocumentSnapshot document = future.get().getDocuments().get(0);

        if (document.exists()) {
            db.document(document.getId()).delete();
            log.info("User deleted");
            return new ResponseEntity<>("User deleted", HttpStatus.OK);
        } else {
            log.info("User not found");
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> loginUser(LoginRequest loginRequest) {

        return null;
    }
}
