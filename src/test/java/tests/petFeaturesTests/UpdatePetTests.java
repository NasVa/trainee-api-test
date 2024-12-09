package tests.petFeaturesTests;

import org.junit.jupiter.api.Test;
import org.nasva.generators.PetGenerator;
import org.nasva.models.PetDTO;
import org.nasva.response.PetResponse;
import org.nasva.services.PetService;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdatePetTests extends BaseTest {

    private final String notFoundMessage = "not found";
    @Test
    public void updateExistingPet() {
        PetDTO updateDTO = PetGenerator.generateAddPetDto();
        updateDTO.setId(existingPet.getId());
        PetDTO updatePetResponse = PetService.updatePet(
                        updateDTO, 200)
                .extract().as(PetDTO.class);
        assertThat(updateDTO).usingRecursiveComparison().isEqualTo(updatePetResponse);
    }
    @Test
    public void updateExistingPetWithFormData(){
        PetDTO updateDTO = PetGenerator.generateAddPetDto();
        updateDTO.setId(existingPet.getId());
        PetResponse petResponse = PetService.updatePetWithFormData(
                        updateDTO, 200);
        assertThat(Long.parseLong(petResponse.getMessage())).isEqualTo(existingPet.getId());
    }
    @Test
    public void updateNotExistingPetWithFormData(){
        PetDTO updateDTO = PetGenerator.generateAddPetDto();
        updateDTO.setId(NOT_EXISTING);
        PetResponse petResponse = PetService.updatePetWithFormData(
                        updateDTO, 404);
        assertThat(petResponse.getMessage()).isEqualTo(notFoundMessage);
    }

}
