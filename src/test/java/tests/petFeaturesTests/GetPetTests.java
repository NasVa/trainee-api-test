package tests.petFeaturesTests;

import org.junit.jupiter.api.Test;
import org.nasva.models.PetDTO;
import org.nasva.services.PetService;

import static org.assertj.core.api.Assertions.assertThat;

public class GetPetTests extends BaseTest {

    @Test
    public void getPetByExistingId() {
        PetDTO petResponseDTO = PetService.getPet(existingPet.getId(), 200)
                .extract().as(PetDTO.class);
        assertThat(existingPet).usingRecursiveComparison().isEqualTo(petResponseDTO);
    }

    @Test
    public void addPetWithNotExistingId() {
        PetService.getPet(NOT_EXISTING, 404);
    }
}
