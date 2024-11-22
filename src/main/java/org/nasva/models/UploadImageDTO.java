package org.nasva.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.io.File;

@Builder
@JsonSerialize
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UploadImageDTO {
    private Long petId;
    private String additionalMetadata;
    private File file;
}
