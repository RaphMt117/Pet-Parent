package api.petparent.application.core.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class TaskDTO {
    private String taskId;
    private String taskName;
    private String taskDesc;
    private String taskDate;
    private String taskPet;
}
