package tests.petFeaturesTests;

import org.junit.jupiter.api.Test;
import org.nasva.models.PetDTO;
import org.nasva.services.PetService;
import org.nasva.specifications.Specifications;

import static org.assertj.core.api.Assertions.assertThat;

public class GetPetTests extends BaseTest {

    @Test
    public void getPetByExistingId() {
        PetDTO petResponseDTO = PetService.getPet(existingPet.getId())
                .spec(Specifications.responseSpec(200))
                .extract().as(PetDTO.class);
        assertThat(existingPet).usingRecursiveComparison().isEqualTo(petResponseDTO);
    }

    @Test
    public void addPetWithNotExistingId() {
        PetService.getPet(NOT_EXISTING)
                .spec(Specifications.responseSpec(200));
    }
}
