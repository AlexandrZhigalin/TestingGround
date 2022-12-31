package com.it_cgm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OverallStatisticsHolder {
    @JsonIgnore
    private String requestUuid;
    @JsonProperty
    private String text;
    @JsonProperty
    private List<CharacterMetadata> data;
}
