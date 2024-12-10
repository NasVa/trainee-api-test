package org.nasva.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PetStatus {
    AVAILABLE,
    PENDING,
    SOLD;

    @JsonValue
    final String value() {
        return this.name().toLowerCase();
    }
}
