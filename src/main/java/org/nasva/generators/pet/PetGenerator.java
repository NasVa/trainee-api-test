package org.nasva.generators.pet;

import com.github.javafaker.Faker;
import org.nasva.models.AddPetDTO;

import java.util.List;

public class PetGenerator {
    private static final Long ID = 653621L;
    public static AddPetDTO generateAddPetDto(long id){
        Faker faker = new Faker();
        return AddPetDTO.builder()
                .name(faker.animal().name())
                .photoUrls(List.of(faker.internet().url()))
                .id(id)
                .category(PetCategoryGenerator.generateCorrectPetCategory(faker))
                .tags(List.of(PetTagGenerator.generateCorrectTagCategory(faker)))
                .status(PetStatusGenerator.generateStatus())
                .build();
    }

    public static AddPetDTO generateAddPetDto(){
        return generateAddPetDto(ID);
    }
}
