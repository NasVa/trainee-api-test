package org.nasva.services;

import io.restassured.response.ValidatableResponse;
import org.aeonbits.owner.ConfigFactory;
import org.nasva.config.UrlsConfig;
import org.nasva.constants.ContentTypes;
import org.nasva.models.PetDTO;
import org.nasva.models.UploadImageDTO;
import org.nasva.response.PetResponse;
import org.nasva.specifications.Specifications;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PetService {
    private static final UrlsConfig urlsConfig = ConfigFactory.create(UrlsConfig.class, System.getProperties());
    private static final String PET_URL = urlsConfig.baseUrl() + "/pet/";

    public static PetDTO addPet(PetDTO petDTO) {
        return given()
                    .spec(Specifications.requestSpecifications(PET_URL, ContentTypes.APPLICATION_JSON))
                    .body(petDTO).
                when()
                    .post().
                then().log().body()
                    .spec(Specifications.responseSpec(200))
                    .extract().as(PetDTO.class);
    }
    public static ValidatableResponse getPet(Long id, int expectedStatusCode) {
        return given()
                    .spec(Specifications.requestSpecifications(PET_URL + id,
                            ContentTypes.APPLICATION_JSON)).
                when()
                    .get().
                then().log().body()
                    .spec(Specifications.responseSpec(expectedStatusCode));

    }
    public static ValidatableResponse findPetByStatus(String status, int expectedStatusCode) {
        return given().log().body()
                    .spec(Specifications.requestSpecifications(PET_URL + "findByStatus",
                            ContentTypes.APPLICATION_JSON))
                    .queryParam("status", status.toLowerCase()).
                when()
                    .get().
                then().log().body()
                    .spec(Specifications.responseSpec(expectedStatusCode));

    }
    public static ValidatableResponse updatePet(PetDTO petDTO, int expectedStatusCode) {
        return given().log().body()
                    .spec(Specifications.requestSpecifications(PET_URL,
                            ContentTypes.APPLICATION_JSON)).
                    body(petDTO).
                when()
                    .put().
                then().log().body()
                    .spec(Specifications.responseSpec(expectedStatusCode));

    }
    public static PetResponse updatePetWithFormData(PetDTO updateDTO, int expectedStatusCode) {
        return given().log().params()
                    .spec(Specifications.requestSpecifications(PET_URL + updateDTO.getId(),
                            ContentTypes.APPLICATION_URLENCODED))
                            .formParams(Map.of(
                                    "name", updateDTO.getName(),
                                    "status", updateDTO.getStatus()
                            )).
                when()
                    .post().
                then().log().body()
                    .spec(Specifications.responseSpec(expectedStatusCode))
                    .extract().as(PetResponse.class);

    }
    public static ValidatableResponse uploadImage(UploadImageDTO uploadImageDTO, int expectedStatusCode) {
        return given().log().params()
                    .spec(Specifications.requestSpecifications(PET_URL + uploadImageDTO.getPetId() + "/uploadImage",
                            ContentTypes.MULTIPART_FORM_DATA))
                    .multiPart("file", uploadImageDTO.getFile(), "image/png")
                    .multiPart("fileName", uploadImageDTO.getFile().getName())
                    .multiPart("additionalMetadata", uploadImageDTO.getAdditionalMetadata()).
                when()
                    .post().
                then().log().body()
                    .spec(Specifications.responseSpec(expectedStatusCode));

    }

    public static void deletePets(List<PetDTO> addedPets) {
        for (PetDTO pet : addedPets) {
            deletePetById(pet.getId(), 200);
        }
    }

    public static void deletePetById(Long id, int expectedStatusCode) {
        given().
        when()
            .spec(Specifications.requestSpecifications(PET_URL + id,
                    ContentTypes.APPLICATION_JSON))
            .delete().
        then()
            .spec(Specifications.responseSpec(expectedStatusCode));
    }

}
