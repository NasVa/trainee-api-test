package org.nasva.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PetStatus{
    AVAILABLE,
    PENDING,
    SOLD;

    @JsonValue
    public String getValue() {
        return name().toLowerCase();
    }
}