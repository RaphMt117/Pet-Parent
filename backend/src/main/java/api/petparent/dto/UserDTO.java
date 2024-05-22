package api.petparent.dto;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String fullName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
