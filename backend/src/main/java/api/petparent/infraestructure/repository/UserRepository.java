package api.petparent.infraestructure.repository;

import api.petparent.infraestructure.web.requests.user.LoginRequest;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;

@Getter
@Setter
@Repository
public class UserRepository{

    public String addUser(LoginRequest loginRequest) throws ExecutionException, InterruptedException {
        CollectionReference db = FirestoreClient.getFirestore().collection("Users");

        ApiFuture<DocumentReference> newUser = db.add(loginRequest);

        return newUser.get().getFirestore().toString();
    }

}
