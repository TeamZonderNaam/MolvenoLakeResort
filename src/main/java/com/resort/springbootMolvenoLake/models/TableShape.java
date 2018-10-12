package com.resort.springbootMolvenoLake.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TableShape {
    RECTANGLE, ROUND;

    @JsonValue
    public int toValue() {
        return ordinal();
    }
}