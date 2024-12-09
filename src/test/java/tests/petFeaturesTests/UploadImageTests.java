package tests.petFeaturesTests;

import org.junit.jupiter.api.Test;
import org.nasva.models.UploadImageDTO;
import org.nasva.response.PetResponse;
import org.nasva.services.PetService;
import org.nasva.specifications.Specifications;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class UploadImageTests extends BaseTest {
    private final String SOME_DATA = "some data";
    private final String FILE_PATH = "img/kitik.jpg";
    private final String MESSAGE_TEMPLATE = "additionalMetadata: %s\nFile uploaded to ./%s, %s bytes";

    @Test
    public void uploadImageOfExistingPet() throws URISyntaxException {
        UploadImageDTO uploadImageDTO = UploadImageDTO.builder()
                .additionalMetadata(SOME_DATA)
                .file(Paths.get(UploadImageTests.class.getClassLoader().getResource(FILE_PATH).toURI()).toFile())
                .petId(existingPet.getId())
                .build();
        PetResponse response = PetService.uploadImage(
                        uploadImageDTO)
                .spec(Specifications.responseSpec(200))
                .extract().as(PetResponse.class);
        String expectedMessage = String.format(String.format(MESSAGE_TEMPLATE, uploadImageDTO.getAdditionalMetadata(),
                uploadImageDTO.getFile().getName(), uploadImageDTO.getFile().length()));
        assertThat(expectedMessage).isEqualTo(response.getMessage());
    }
}
