package tests.petFeaturesTests;

import org.junit.jupiter.api.Test;
import org.nasva.services.PetService;

public class DeletePetTests {
    public static final Long NOT_EXISTING_ID = -10L;
    @Test
    public void deleteNotExistingPet(){
        PetService.deletePetById(NOT_EXISTING_ID, 404);

    }

}
