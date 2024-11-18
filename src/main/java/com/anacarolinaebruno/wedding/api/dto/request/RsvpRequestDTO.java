package com.anacarolinaebruno.wedding.api.dto.request;

import com.anacarolinaebruno.wedding.api.validation.annotation.EmailOrPhone;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RsvpRequestDTO {

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 100, message = "O nome não pode ter mais que 100 caracteres.")
    private String name;

    @NotBlank(message = "O email é obrigatório.")
    @EmailOrPhone(message = "O email ou telefone deve ser válido.")
    @Size(max = 100, message = "O email não pode ter mais que 100 caracteres.")
    private String email;

    @NotNull(message = "A confirmação é obrigatória.")
    private Boolean confirmation;

    @NotNull(message = "O número de convidados é obrigatório")
    @Min(value = 0, message = "O número de convidados deve ser no mínimo 0.")
    @Max(value = 5, message = "O número máximo de convidados permitidos é 5.")
    private Integer guestsAmount;

    @Size(max = 500, message = "A mensagem não pode ter mais que 500 caracteres.")
    private String message;

}
