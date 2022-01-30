package com.localbakery.api.controller.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class ResponseContainer {

    @JsonProperty("rCode")
    private final String rCode;
    @JsonProperty("rMessage")
    private final String rMessage;
    @JsonProperty("rData")
    private final Object rData;

    public static ResponseContainer ok(Object rData) {
        return  ResponseContainer.builder()
                .rCode("200")
                .rMessage("OK")
                .rData(rData)
                .build();
    }
}
