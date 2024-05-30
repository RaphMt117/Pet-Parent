package api.petparent.application.core.dto;

import lombok.*;

@Data
@RequiredArgsConstructor
public class UserDTO {
    private String userId;
    private String fullName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
