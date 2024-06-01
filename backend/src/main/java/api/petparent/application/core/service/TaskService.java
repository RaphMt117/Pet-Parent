package api.petparent.application.core.service;

import api.petparent.application.core.dto.TaskDTO;
import api.petparent.infraestructure.web.requests.AddTaskRequestModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public interface TaskService {
    ResponseEntity<String> addTask(String userId, AddTaskRequestModel taskRequest) throws ExecutionException, InterruptedException;

    ResponseEntity<List<TaskDTO>> getTasks(String userId) throws ExecutionException, InterruptedException;

    ResponseEntity<String> deleteTask(String taskId, String userId) throws ExecutionException, InterruptedException;

    ResponseEntity<TaskDTO> getTask(String taskId, String userId);

    ResponseEntity<String> updateTask(String taskId, String userId, AddTaskRequestModel taskRequest);
}
