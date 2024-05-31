package api.petparent;

import api.petparent.infraestructure.repository.RepositoryRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetparentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetparentApplication.class, args);
		RepositoryRunner repositoryRunner = new RepositoryRunner();
        try {
            repositoryRunner.initFirebase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}


