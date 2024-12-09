package tests.petFeaturesTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.Test;
import org.nasva.models.PetDTO;
import org.nasva.services.PetService;
import org.nasva.specifications.Specifications;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FindPetTests extends BaseTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String NOT_EXISTING_STATUS = "incorrect";

    @Test
    public void findExistingPetByStatus() throws JsonProcessingException {
        List<PetDTO> pets = PetService.findPetByStatus(existingPet.getStatus().name())
                .spec(Specifications.responseSpec(200))
                .extract().as(new TypeRef<>() {
                });
        pets.stream().anyMatch(pet -> pet.equals(existingPet));
    }

    @Test
    public void findPetByIdByIncorrectStatus() throws JsonProcessingException {
        String pets = PetService.findPetByStatus(NOT_EXISTING_STATUS)
                .spec(Specifications.responseSpec(200))
                .extract().asString();
        PetDTO[] petsArray = objectMapper.readValue(pets, PetDTO[].class);
        assertThat(petsArray.length).isEqualTo(0);
    }

}
