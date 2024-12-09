package org.nasva.generators;

import com.github.javafaker.Faker;
import org.nasva.models.PetCategoryDTO;
import org.nasva.models.PetDTO;
import org.nasva.models.PetStatus;
import org.nasva.models.PetTagDTO;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PetGenerator {
    private static final Long ID = 653621L;
    public static PetDTO generateAddPetDto(long id){
        Faker faker = new Faker();
        return PetDTO.builder()
                .name(faker.animal().name())
                .photoUrls(List.of(faker.internet().url()))
                .id(id)
                .category(generateCorrectPetCategory(faker))
                .tags(List.of(generateCorrectTagCategory(faker)))
                .status(generateStatus())
                .build();
    }

    public static PetDTO generateAddPetDto(){
        return generateAddPetDto(ID);
    }

    public static PetTagDTO generateCorrectTagCategory(Faker faker) {
        return PetTagDTO.builder()
                .id(1L)
                .name(faker.cat().registry())
                .build();
    }

    public static PetStatus generateStatus() {
        List<PetStatus> list = Arrays.stream(PetStatus.values()).toList();
        return list.get(new Random().nextInt(list.size()));
    }

    public static PetCategoryDTO generateCorrectPetCategory(Faker faker) {
        return PetCategoryDTO.builder()
                .id(1L)
                .name(faker.dog().breed())
                .build();
    }
}
