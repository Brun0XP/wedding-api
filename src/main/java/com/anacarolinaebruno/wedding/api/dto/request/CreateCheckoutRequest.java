package com.anacarolinaebruno.wedding.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
public class CreateCheckoutRequest {

    @JsonProperty("products_id")
    private List<Long> productsId;

    @JsonProperty("firstname")
    private String firstName;

    @JsonProperty("lastname")
    private String lastName;
}
