package api.petparent.application.core.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TaskDTO {
    private String title;
    private String start;
    private String pet;
}
