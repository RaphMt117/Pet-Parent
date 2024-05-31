package api.petparent.infraestructure.repository;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;

public class RepositoryRunner {

    public void initFirebase() throws Exception {

        ClassLoader classLoader = RepositoryRunner.class.getClassLoader();

        File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json").getFile()));

        FileInputStream serviceAccount =
                new FileInputStream(file.getAbsolutePath());

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://petparent-961e5-default-rtdb.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);


    }

}
