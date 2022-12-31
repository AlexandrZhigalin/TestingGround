package com.it_cgm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CharacterMetadata {
    @JsonProperty("character")
    private char ch;
    @JsonProperty("repetitions")
    private int counter = 1;
    @JsonIgnore
    private int position;
}