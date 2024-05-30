package api.petparent.infraestructure.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TasksController {

        @PostMapping("/task/{userId}")
        public ResponseEntity<String> createTask(@PathVariable String userId) {

            return null;
        }

        @GetMapping("/task/{userId}")
        public ResponseEntity<String> addTask(@PathVariable String userId) {

            return null;
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
