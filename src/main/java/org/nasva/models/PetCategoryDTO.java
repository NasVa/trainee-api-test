package org.nasva.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Builder
@JsonSerialize
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PetCategoryDTO {

    private Long id;

    private String name;

}
