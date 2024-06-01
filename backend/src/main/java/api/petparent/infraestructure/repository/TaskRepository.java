package api.petparent.infraestructure.repository;

import api.petparent.application.core.dto.TaskDTO;
import api.petparent.infraestructure.web.requests.AddTaskRequestModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
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
public class TaskRepository {
    public ResponseEntity<String> addTask(String userId, AddTaskRequestModel taskRequest) throws ExecutionException, InterruptedException {
        CollectionReference db = FirestoreClient.getFirestore().collection("pet_parents");

        log.info("Adding task to user: " + userId);

        ApiFuture<DocumentSnapshot> user = db.document(userId).get();

        if (!user.get().exists()) {
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
        }

        if (taskRequest.getTitle() == null || taskRequest.getTitle().isEmpty()) {
            return new ResponseEntity<>("Task title is required", HttpStatus.BAD_REQUEST);
        }

        var newTask = db.document(userId).collection("tasks").add(taskRequest);

        return new ResponseEntity<>("Task added with ID: " + newTask.get().getId(), HttpStatus.OK);
    }

    public ResponseEntity<List<TaskDTO>> getTasks(String userId) throws ExecutionException, InterruptedException {
        CollectionReference db = FirestoreClient.getFirestore().collection("pet_parents");

        log.info("Listing tasks to user:{} ", userId);

        ApiFuture<DocumentSnapshot> user = db.document(userId).get();

        if (!user.get().exists()) {
            throw new ExecutionException("User does not exist", null);
        }

        ApiFuture<QuerySnapshot> future = db.document(userId).collection("tasks").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<TaskDTO> tasks = new ArrayList<>();
        if (documents.isEmpty()) {
            return new ResponseEntity<>(tasks, HttpStatus.NOT_FOUND);
        }

        for (QueryDocumentSnapshot document : documents) {
            TaskDTO task = document.toObject(TaskDTO.class);
            tasks.add(task);
        }

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteTask(String taskId, String userId) throws ExecutionException, InterruptedException {
        CollectionReference db = FirestoreClient.getFirestore().collection("pet_parents");

        log.info("Deleting task: {}, from user: {}", taskId, userId);

        ApiFuture<DocumentSnapshot> user = db.document(userId).get();
        ApiFuture<DocumentSnapshot> task = db.document(userId).collection("tasks").document(taskId).get();

        if (!user.get().exists()) {
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
        }
        if (!task.get().exists()) {
            return new ResponseEntity<>("Task does not exist", HttpStatus.NOT_FOUND);
        }

        db.document(userId).collection("tasks").document(taskId).delete();

        return new ResponseEntity<>("Deleted task with Id: " + taskId, HttpStatus.OK);
    }


    public ResponseEntity<TaskDTO> getTask(String taskId, String userId) {
        CollectionReference db = FirestoreClient.getFirestore().collection("pet_parents");
        ApiFuture<QuerySnapshot> future = db.document().collection("tasks").whereEqualTo("taskId", taskId).get();

        var taskDTO = new TaskDTO();
        try {
            DocumentSnapshot document = future.get().getDocuments().get(0);
            if (document.exists()) {
                taskDTO.setId(document.getId());
                taskDTO.setTitle(document.getString("title"));
                taskDTO.setStart(document.getString("start"));
                taskDTO.setPet(document.getString("pet"));

                return new ResponseEntity<>(taskDTO, HttpStatus.OK);
            } else {
                log.info("Task not found");
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (IndexOutOfBoundsException | ExecutionException | InterruptedException e) {
            log.info("Task not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> updateTask(String taskId, String userId, AddTaskRequestModel taskRequest) {
        return null;
    }
}
