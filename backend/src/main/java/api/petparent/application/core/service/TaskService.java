package api.petparent.application.core.service;

import api.petparent.application.core.dto.TaskDTO;
import api.petparent.infraestructure.web.requests.AddTaskRequestModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Service
public interface TaskService {
    ResponseEntity<String> addTask(String userId, AddTaskRequestModel taskRequest) throws ExecutionException, InterruptedException;

    ResponseEntity<ArrayList<TaskDTO>> getTasks(String userId);

    ResponseEntity<String> deleteTask(String taskId, String userId) throws ExecutionException, InterruptedException;

    ResponseEntity<TaskDTO> getTask(String taskId, String userId);

    ResponseEntity<String> updateTask(String taskId, String userId, AddTaskRequestModel taskRequest);
}
