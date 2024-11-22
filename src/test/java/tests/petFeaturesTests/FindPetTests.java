package tests.petFeaturesTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.nasva.models.PetDTO;
import org.nasva.services.PetService;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class FindPetTests extends BaseTest{
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String NOT_EXISTING_STATUS = "incorrect";
    @Test
    public void findExistingPetByStatus() throws JsonProcessingException {
        String pets = PetService.findPetByStatus(existingPet.getStatus().name(), 200)
                .extract().asString();
        PetDTO[] petsArray = objectMapper.readValue(pets, PetDTO[].class);
        Arrays.stream(petsArray).anyMatch(pet -> pet.equals(existingPet));
    }
    @Test
    public void findPetByIdByIncorrectStatus() throws JsonProcessingException {
        String pets = PetService.findPetByStatus(NOT_EXISTING_STATUS, 200)
                .extract().asString();
        PetDTO[] petsArray = objectMapper.readValue(pets, PetDTO[].class);
        assertThat(petsArray.length).isEqualTo(0);
    }

}
