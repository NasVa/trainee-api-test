package org.nasva.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Builder
@JsonSerialize
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AddPetDTO {
    private String name;

    private List<String> photoUrls;

    private Long id;

    private PetCategoryDTO category;

    private List<PetTagDTO> tags;

    private PetStatus status;

    @Override
    public int hashCode() {
        return Objects.hash(
                name,
                photoUrls,
                id,
                category,
                tags,
                status
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof AddPetDTO)){
            return false;
        }
        AddPetDTO that = (AddPetDTO) obj;
        return Objects.equals(that.id, this.id) &&
                Objects.equals(that.name, this.name) &&
                Objects.equals(that.photoUrls, this.photoUrls) &&
                that.tags.equals(this.tags) &&
                that.category.equals(this.category) &&
                that.status == this.status;
    }
}
