package tests.petFeaturesTests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.nasva.generators.PetGenerator;
import org.nasva.models.PetDTO;
import org.nasva.services.PetService;
import org.nasva.specifications.Specifications;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {
    public static final List<PetDTO> addedPets = new ArrayList<>();
    public static final Long NOT_EXISTING = -10L;
    public static PetDTO existingPet;

    @BeforeAll
    public static void addPet() {
        PetDTO petRequestDTO = PetGenerator.generateAddPetDto();
        existingPet = PetService.addPet(petRequestDTO)
                .spec(Specifications.responseSpec(200))
                .extract().as(PetDTO.class);
        ;
        addedPets.add(existingPet);
    }

    @AfterAll
    public static void deletePets() {
        PetService.deletePets(addedPets);
    }
}
