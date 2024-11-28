package tests.petFeaturesTests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nasva.generators.PetGenerator;
import org.nasva.models.AddPetDTO;
import org.nasva.services.PetService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AddPetTests {
    private static final List<AddPetDTO> addedPets = new ArrayList<>();
    private static final int NEGATIVE_ID = -10;

    @Test
    public void addPetCorrect() {
        AddPetDTO petRequestDTO = PetGenerator.generateAddPetDto();
        AddPetDTO petResponseDTO = PetService.addPet(petRequestDTO);
        addedPets.add(petResponseDTO);
        assertThat(petRequestDTO).usingRecursiveComparison().isEqualTo(petResponseDTO);
    }

    @Test
    public void addPetWithNegativeId() {
        AddPetDTO petRequestDTO = PetGenerator.generateAddPetDto(NEGATIVE_ID);
        AddPetDTO petResponseDTO = PetService.addPet(petRequestDTO);
        addedPets.add(petResponseDTO);
        Assertions.assertTrue(petResponseDTO.getId() > 0);
    }

    @AfterAll
    public static void deletePets() {
        PetService.deletePets(addedPets);
    }
}
