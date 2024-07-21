package com.anacarolinaebruno.wedding.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutResponse {

    @JsonProperty("mercadopago_preference_id")
    private String mercadopagoPreferenceId;
}