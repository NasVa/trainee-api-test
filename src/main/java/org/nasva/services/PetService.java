package org.nasva.services;

import io.restassured.http.ContentType;
import org.aeonbits.owner.ConfigFactory;
import org.nasva.config.ServerConfig;
import org.nasva.models.AddPetDTO;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PetService {
    private static final ServerConfig urlsConfig = ConfigFactory.create(ServerConfig.class, System.getProperties());
    private static final String PET_URL = urlsConfig.getBaseUrl() + "/pet/";

    public static AddPetDTO addPet(AddPetDTO petDTO) {
        return given().log().uri()
                .contentType(ContentType.JSON)
                .body(petDTO).
                when()
                .post(PET_URL).
                then().log().body()
                .statusCode(200)
                .extract().as(AddPetDTO.class);
    }

    public static void deletePets(List<AddPetDTO> addedPets) {
        for (AddPetDTO pet : addedPets) {
            given().log().body().
                    when()
                    .delete(PET_URL + pet.getId()).
                    then().log().body()
                    .statusCode(200);
        }
    }

}
