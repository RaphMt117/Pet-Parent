package api.petparent.infraestructure.web.requests.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}


