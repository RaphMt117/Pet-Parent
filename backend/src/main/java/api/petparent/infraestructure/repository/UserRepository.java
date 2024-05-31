package api.petparent.infraestructure.repository;

import api.petparent.infraestructure.web.requests.user.LoginRequest;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;

@Slf4j
@Getter
@Setter
@Repository
public class UserRepository{


    public String addUser(LoginRequest loginRequest) throws ExecutionException, InterruptedException {
        CollectionReference db = FirestoreClient.getFirestore().collection("Users");

        ApiFuture<DocumentReference> newUser = db.add(loginRequest);

        return newUser.get().getFirestore().toString();
    }

    public String deleteUser(String email) throws ExecutionException, InterruptedException {
        CollectionReference db = FirestoreClient.getFirestore().collection("Users");

        ApiFuture<QuerySnapshot> future = db.whereEqualTo("email", email).get();

        try {
            future.get().getDocuments().get(0);
        } catch (IndexOutOfBoundsException e) {
            log.info("User not found");
            return "User not found";
        }
        DocumentSnapshot document = future.get().getDocuments().get(0);

        if (document.exists()) {
            db.document(document.getId()).delete();
            log.info("User deleted");
            return "User deleted";
        } else {
            log.info("User not found");
            return "User not found";
        }
    }
}
