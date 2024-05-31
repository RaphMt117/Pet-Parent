package api.petparent.infraestructure.web.controllers;

import api.petparent.application.core.dto.TaskDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TasksController {

        @PostMapping("/task/{userId}")
        public ResponseEntity<String> addTask(@PathVariable String userId) {

            return null;
        }

        @GetMapping("/tasks/{userId}")
        public ResponseEntity<String> getTasks(@PathVariable String userId) {

            return new ResponseEntity<>("ok", HttpStatus.OK);
        }

        @DeleteMapping("/tasks/{userId}/{taskId}")
        public ResponseEntity<String> deleteTask(@PathVariable String taskId, @PathVariable String userId) {

            return null;
        }

        @PatchMapping("/tasks/{userId}/{taskId}")
        public ResponseEntity<String> updateTask(@PathVariable String taskId, @PathVariable String userId) {

            return null;
        }
}
