package org.nasva.generators.pet;

import com.github.javafaker.Faker;
import org.nasva.models.PetCategoryDTO;

public class PetCategoryGenerator {
    public static PetCategoryDTO generateCorrectPetCategory(Faker faker){
        return PetCategoryDTO.builder()
                .id(1L)
                .name(faker.dog().breed())
                .build();
    }
}
