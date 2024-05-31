package api.petparent.application.core.service;

import api.petparent.application.core.dto.TaskDTO;
import api.petparent.infraestructure.web.requests.AddTaskRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface TaskService {
    ResponseEntity<String> addTask(String userId, AddTaskRequestModel taskRequest);

    ResponseEntity<ArrayList<TaskDTO>> getTasks(String userId);

    ResponseEntity<String> deleteTask(String taskId, String userId);

    ResponseEntity<TaskDTO> getTask(String taskId, String userId);

    ResponseEntity<String> updateTask(String taskId, String userId, AddTaskRequestModel taskRequest);
}
