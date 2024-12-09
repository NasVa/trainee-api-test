package org.nasva.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@Builder
@JsonSerialize
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PetResponse {
    private Integer code;
    private String type;
    private String message;
}
