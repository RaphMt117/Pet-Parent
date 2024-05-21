package api.petparent.requests.user;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}


