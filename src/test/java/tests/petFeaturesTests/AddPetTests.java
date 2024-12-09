package tests.petFeaturesTests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nasva.generators.PetGenerator;
import org.nasva.models.PetDTO;
import org.nasva.services.PetService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AddPetTests {

    private static final List<PetDTO> addedPets = new ArrayList<>();
    private static final Long NEGATIVE_ID = -10L;

    @Test
    public void addPetCorrect(){
        PetDTO petRequestDTO = PetGenerator.generateAddPetDto();
        PetDTO petResponseDTO = PetService.addPet(petRequestDTO);
        addedPets.add(petResponseDTO);
        assertThat(petRequestDTO).usingRecursiveComparison().isEqualTo(petResponseDTO);
    }

    @Test
    public void addPetWithNegativeId(){
        PetDTO petRequestDTO = PetGenerator.generateAddPetDto(NEGATIVE_ID);
        PetDTO petResponseDTO = PetService.addPet(petRequestDTO);
        addedPets.add(petResponseDTO);
        Assertions.assertTrue(petResponseDTO.getId() > 0);
    }

    @AfterAll
    public static void deletePets(){
        PetService.deletePets(addedPets);
    }
}
