package api.petparent.infraestructure.web.controllers;

import api.petparent.application.core.dto.TaskDTO;
import api.petparent.application.core.service.TaskService;
import api.petparent.infraestructure.web.requests.AddTaskRequestModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("petparent/api/v1/task")
public class TasksController {

    @Autowired
    private final TaskService taskService;

    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> addTask(@PathVariable String userId, @RequestBody AddTaskRequestModel taskRequest) {

        var response = taskService.addTask(userId, taskRequest);

        return response;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ArrayList<TaskDTO>> getTasks(@PathVariable String userId) {

        var tasks = taskService.getTasks(userId);

        return tasks;
    }

    @DeleteMapping("/{userId}/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable String taskId, @PathVariable String userId) {

        var response = taskService.deleteTask(taskId, userId);

        return response;
    }

    @DeleteMapping("/{userId}/{taskId}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable String taskId, @PathVariable String userId) {

        var response = taskService.getTask(taskId, userId);

        return response;
    }

    @PatchMapping("/{userId}/{taskId}")
    public ResponseEntity<String> updateTask(@PathVariable String taskId, @PathVariable String userId, @RequestBody AddTaskRequestModel taskModel) {

        var response = taskService.updateTask(taskId, userId, taskModel);

        return response;
    }
}
