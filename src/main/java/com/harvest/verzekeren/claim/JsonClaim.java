package com.harvest.verzekeren.claim;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
class JsonClaim
{
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String kenteken;
}
