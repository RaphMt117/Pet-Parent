package api.petparent.application.core.service.Impl;

import api.petparent.application.core.dto.TaskDTO;
import api.petparent.application.core.service.TaskService;
import api.petparent.infraestructure.repository.TaskRepository;
import api.petparent.infraestructure.web.requests.AddTaskRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    @Autowired
    private final TaskRepository taskRepository;

    @Override
    public ResponseEntity<String> addTask(String userId, AddTaskRequestModel taskRequest) throws ExecutionException, InterruptedException {
        var response = taskRepository.addTask(userId, taskRequest);
        return response;
    }

    @Override
    public ResponseEntity<List<TaskDTO>> getTasks(String userId) throws ExecutionException, InterruptedException {
        var response = taskRepository.getTasks(userId);
        return response;
    }

    @Override
    public ResponseEntity<String> deleteTask(String taskId, String userId) throws ExecutionException, InterruptedException {
        var response = taskRepository.deleteTask(taskId, userId);
        return response;
    }

    @Override
    public ResponseEntity<TaskDTO> getTask(String taskId, String userId) {
        var response = taskRepository.getTask(taskId, userId);
        return response;
    }

    @Override
    public ResponseEntity<String> updateTask(String taskId, String userId, AddTaskRequestModel taskRequest) {
        var response = taskRepository.updateTask(taskId, userId, taskRequest);
        return null;
    }
}
