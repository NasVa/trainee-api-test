package tests.petFeaturesTests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nasva.generators.pet.PetGenerator;
import org.nasva.models.AddPetDTO;
import org.nasva.services.PetService;

import java.util.ArrayList;
import java.util.List;

public class AddPetTests {

    private static final List<AddPetDTO> addedPets = new ArrayList<>();
    private final int NEGATIVE_ID = -10;

    @Test
    public void addPetCorrect(){
        AddPetDTO petRequestDTO = PetGenerator.generateAddPetDto();
        AddPetDTO petResponseDTO = PetService.addPet(petRequestDTO);
        Assertions.assertEquals(petRequestDTO, petResponseDTO);
    }

    @Test
    public void addPetWithNegativeId(){
        AddPetDTO petRequestDTO = PetGenerator.generateAddPetDto(NEGATIVE_ID);
        AddPetDTO petResponseDTO = PetService.addPet(petRequestDTO);
        Assertions.assertTrue(petResponseDTO.getId() > 0);
    }


    @AfterAll
    public static void deletePets(){
        PetService.deletePets(addedPets);
    }

}
