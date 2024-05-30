package api.petparent.application.core.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PetDTO {
    private String petId;
    private String petName;
    private String petSpecies;
    private String petRace;
    private String petSize;
    private String petGender;
    private String petColor;
    private Integer petAge;
}
