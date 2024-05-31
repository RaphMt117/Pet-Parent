package api.petparent.infraestructure.web.requests;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddTaskRequestModel {
    private String taskId;
    private String taskName;
    private String taskDesc;
    private String taskDate;
    private String taskPet;
}
