package org.nasva.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@Builder
@JsonSerialize
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UpdatePetDTO {
    private String name;
    private Long petId;
    private PetStatus status;
}
