package tests.petFeaturesTests;

import org.junit.jupiter.api.Test;
import org.nasva.services.PetService;
import org.nasva.specifications.Specifications;

public class DeletePetTests {
    public static final Long NOT_EXISTING_ID = -10L;

    @Test
    public void deleteNotExistingPet() {
        PetService.deletePetById(NOT_EXISTING_ID)
                .spec(Specifications.responseSpec(404));
    }

}
