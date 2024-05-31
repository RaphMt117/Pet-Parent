package api.petparent.infraestructure.web.requests;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPetRequestModel {
    private String petName;
    private String petSpecies;
    private String petRace;
    private String petSize;
    private String petGender;
    private String petColor;
    private String petAge;
}
