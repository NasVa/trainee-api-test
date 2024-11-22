package org.nasva.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.util.List;

@Builder
@JsonSerialize
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PetDTO {
    private String name;
    private List<String> photoUrls;
    private Long id;
    private PetCategoryDTO category;
    private List<PetTagDTO> tags;
    private PetStatus status;
}
