package org.nasva.generators.pet;

import com.github.javafaker.Faker;
import org.nasva.models.PetTagDTO;

public class PetTagGenerator {
    public static PetTagDTO generateCorrectTagCategory(Faker faker){
        return PetTagDTO.builder()
                .id(1L)
                .name(faker.cat().registry())
                .build();
    }
}
