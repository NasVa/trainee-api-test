package org.nasva.services;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.aeonbits.owner.ConfigFactory;
import org.nasva.config.UrlsConfig;
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

    public static ValidatableResponse addPet(PetDTO petDTO) {
        return given()
                .spec(Specifications.requestSpecifications(PET_URL, ContentType.JSON))
                .body(petDTO).
                when()
                .post().
                then().log().body();
    }

    public static ValidatableResponse getPet(Long id) {
        return given()
                .spec(Specifications.requestSpecifications(PET_URL + id,
                        ContentType.JSON)).
                when()
                .get().
                then().log().body();

    }

    public static ValidatableResponse findPetByStatus(String status) {
        return given().log().body()
                .spec(Specifications.requestSpecifications(PET_URL + "findByStatus",
                        ContentType.JSON))
                .queryParam("status", status.toLowerCase()).
                when()
                .get().
                then().log().body();

    }

    public static ValidatableResponse updatePet(PetDTO petDTO) {
        return given().log().body()
                .spec(Specifications.requestSpecifications(PET_URL,
                        ContentType.JSON)).
                body(petDTO).
                when()
                .put().
                then().log().body();

    }

    public static ValidatableResponse updatePetWithFormData(PetDTO updateDTO) {
        return given().log().params()
                .spec(Specifications.requestSpecifications(PET_URL + updateDTO.getId(),
                        ContentType.URLENC))
                .formParams(Map.of(
                        "name", updateDTO.getName(),
                        "status", updateDTO.getStatus()
                )).
                when()
                .post().
                then().log().body();

    }

    public static ValidatableResponse uploadImage(UploadImageDTO uploadImageDTO) {
        return given().log().params()
                .spec(Specifications.requestSpecifications(PET_URL + uploadImageDTO.getPetId() + "/uploadImage",
                        ContentType.MULTIPART))
                .multiPart("file", uploadImageDTO.getFile(), "image/png")
                .multiPart("fileName", uploadImageDTO.getFile().getName())
                .multiPart("additionalMetadata", uploadImageDTO.getAdditionalMetadata()).
                when()
                .post().
                then().log().body();

    }

    public static void deletePets(List<PetDTO> addedPets) {
        for (PetDTO pet : addedPets) {
            deletePetById(pet.getId())
                    .spec(Specifications.responseSpec(200));
        }
    }

    public static ValidatableResponse deletePetById(Long id) {
        return given().
                when()
                .spec(Specifications.requestSpecifications(PET_URL + id,
                        ContentType.JSON))
                .delete().
                then();
    }

}
