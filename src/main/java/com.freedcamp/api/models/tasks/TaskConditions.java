package com.freedcamp.api.models.tasks;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskConditions {

    @JsonProperty("filter")
    private Object filter;

    @JsonProperty("f_use_and")
    private String fUseAnd;

    @JsonProperty("substring")
    private String substring;

    @JsonProperty("order")
    private Object order;
}