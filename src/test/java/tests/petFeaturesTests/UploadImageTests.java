package tests.petFeaturesTests;

import org.junit.jupiter.api.Test;
import org.nasva.generators.PetGenerator;
import org.nasva.models.PetDTO;
import org.nasva.models.UploadImageDTO;
import org.nasva.response.PetResponse;
import org.nasva.services.PetService;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class UploadImageTests extends BaseTest {
    private final String someData = "some data";
    private final String filePath = "img/kitik.jpg";
    @Test
    public void uploadImageOfExistingPet() throws URISyntaxException {
        UploadImageDTO uploadImageDTO = UploadImageDTO.builder()
                .additionalMetadata(someData)
                .file(Paths.get(UploadImageTests.class.getClassLoader().getResource(filePath).toURI()).toFile())
                .petId(existingPet.getId())
                .build();
        PetResponse response = PetService.uploadImage(
                uploadImageDTO, 200)
                .extract().as(PetResponse.class);
        String expectedMessage = "additionalMetadata: " + uploadImageDTO.getAdditionalMetadata() + "\nFile uploaded to ./"+
                uploadImageDTO.getFile().getName() + ", " + uploadImageDTO.getFile().length() + " bytes";
        assertThat(expectedMessage).isEqualTo(response.getMessage());
    }
}
