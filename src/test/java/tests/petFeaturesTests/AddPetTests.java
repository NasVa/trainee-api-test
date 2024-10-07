package tests.petFeaturesTests;

import com.sun.source.tree.AssertTree;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nasva.models.AddPetDTO;
import org.nasva.models.PetCategoryDTO;
import org.nasva.models.PetStatus;
import org.nasva.models.PetTagDTO;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class AddPetTests {

    private static String baseUrl = "https://petstore.swagger.io/v2";
    private static List<AddPetDTO> addedPets = new ArrayList<>();

    @Test
    public void addPetCorrect(){
        PetCategoryDTO petCategoryDTO = PetCategoryDTO.builder()
                .id(1L)
                .name("Cats")
                .build();
        PetTagDTO petTagDTO = PetTagDTO.builder()
                .id(1L)
                .name("Cute")
                .build();
        AddPetDTO petRequestDTO = AddPetDTO.builder()
                        .name("Kotik")
                        .photoUrls(List.of("img/kotik.png"))
                        .id(10L)
                        .category(petCategoryDTO)
                        .tags(List.of(petTagDTO))
                        .status(PetStatus.AVAILABLE)
                        .build();
        AddPetDTO addPetResponse = given().log().body()
                .contentType(ContentType.JSON)
                .body(petRequestDTO).
        when()
                .post(baseUrl + "/pet").
        then().log().body()
                .statusCode(200)
                .extract().as(AddPetDTO.class);
        addedPets.add(petRequestDTO);
        Assertions.assertEquals(petRequestDTO, addPetResponse);
    }

    @Test
    public void addPetWithNegativeId(){
        PetCategoryDTO petCategoryDTO = PetCategoryDTO.builder()
                .id(1L)
                .name("Cats")
                .build();
        PetTagDTO petTagDTO = PetTagDTO.builder()
                .id(1L)
                .name("Cute")
                .build();
        AddPetDTO petRequestDTO = AddPetDTO.builder()
                .name("Kotik")
                .photoUrls(List.of("img/kotik.png"))
                .id(-10L)
                .category(petCategoryDTO)
                .tags(List.of(petTagDTO, petTagDTO))
                .status(PetStatus.SOLD)
                .build();
        AddPetDTO responsePetDto = given().log().body()
                .contentType(ContentType.JSON)
                .body(petRequestDTO).
        when()
                .post(baseUrl + "/pet").
                then().log().body()
                .statusCode(200)
                .extract().as(AddPetDTO.class);
        Assertions.assertTrue(responsePetDto.getId() > 0);
    }

    @Test
    public void addPetWithWrongMethodType() {
        PetCategoryDTO petCategoryDTO = PetCategoryDTO.builder()
                .id(1L)
                .name("Cats")
                .build();
        PetTagDTO petTagDTO = PetTagDTO.builder()
                .id(1L)
                .name("Cute")
                .build();
        AddPetDTO petRequestDTO = AddPetDTO.builder()
                .name("Kotik")
                .photoUrls(List.of("img/kotik.png"))
                .id(10L)
                .category(petCategoryDTO)
                .tags(List.of(petTagDTO, petTagDTO))
                .status(PetStatus.SOLD)
                .build();
        given().log().body()
                .contentType(ContentType.JSON)
                .body(petRequestDTO).
                when()
                .get(baseUrl + "/pet").
                then().log().body()
                .statusCode(405);
    }

    @AfterAll
    public static void deletePets(){
        for(AddPetDTO pet : addedPets){
            given().log().body().
            when()
            .delete(baseUrl + "/pet/" + pet.getId()).
            then().log().body()
            .statusCode(200);
        }
    }

}
