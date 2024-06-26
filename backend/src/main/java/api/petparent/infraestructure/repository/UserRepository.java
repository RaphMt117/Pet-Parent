package api.petparent.infraestructure.repository;

import api.petparent.application.core.dto.UserDTO;
import api.petparent.infraestructure.web.requests.LoginRequest;
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
        CollectionReference db = FirestoreClient.getFirestore().collection("pet_parents");

        ApiFuture<QuerySnapshot> future = db.whereEqualTo("email", loginRequest.getEmail()).get();

        if (!future.get().isEmpty()) {
            log.info("User already exists");
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        }

        ApiFuture<DocumentReference> newUser = db.add(loginRequest);
        ApiFuture<QuerySnapshot> futureUser = db.whereEqualTo("email", loginRequest.getEmail()).get();
        String id = futureUser.get().getDocuments().get(0).getId();

        db.document(id).collection("tasks");

        return new ResponseEntity<>("User added with ID: " + newUser.get().getId(), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteUser(String email) throws ExecutionException, InterruptedException {
        CollectionReference db = FirestoreClient.getFirestore().collection("pet_parents");

        try {
            ApiFuture<QuerySnapshot> future = db.whereEqualTo("email", email).get();
            DocumentSnapshot document = future.get().getDocuments().get(0);
            if (document.exists()) {
                db.document(document.getId()).delete();
                log.info("User deleted");
                return new ResponseEntity<>("User deleted", HttpStatus.OK);
            } else {
                log.info("User not found");
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
        } catch (IndexOutOfBoundsException e) {
            log.info("User not found");
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<String> loginUser(LoginRequest loginRequest) {

        return null;
    }

    public ResponseEntity<UserDTO> getUser(String email, String password) throws ExecutionException, InterruptedException {
        CollectionReference db = FirestoreClient.getFirestore().collection("pet_parents");
        ApiFuture<QuerySnapshot> futureEmail = db.whereEqualTo("email", email).get();
        ApiFuture<QuerySnapshot> futurePassword = db.whereEqualTo("password", password).get();

        if (futureEmail.get().isEmpty()) {
            log.info("User not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        if (futurePassword.get().isEmpty()){
            log.info("Password not incorrect");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        var userDTO = new UserDTO();
        try {
            DocumentSnapshot document = futureEmail.get().getDocuments().get(0);
            if (document.exists()) {
                userDTO.setUserId(document.getId());
                userDTO.setFirstName(document.getString("firstName"));
                userDTO.setLastName(document.getString("lastName"));
                userDTO.setEmail(document.getString("email"));
                log.info("User found");
                return new ResponseEntity<>(userDTO, HttpStatus.OK);
            } else {
                log.info("User not found");
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (IndexOutOfBoundsException | ExecutionException | InterruptedException e) {
            log.info("User not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
