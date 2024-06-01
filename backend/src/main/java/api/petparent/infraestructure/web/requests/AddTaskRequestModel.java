package api.petparent.infraestructure.web.requests;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddTaskRequestModel {
    private String title;
    private String start;
    private String pet;
}
