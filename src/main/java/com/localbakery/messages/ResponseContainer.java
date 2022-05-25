package com.localbakery.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class ResponseContainer <T> {

    @JsonProperty("rCode")
    private final String rCode;
    @JsonProperty("rMessage")
    private final String rMessage;
    @JsonProperty("rData")
    private final T rData;
}
