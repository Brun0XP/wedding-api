package com.anacarolinaebruno.wedding.api.dto.response;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RsvpResponseDTO {

    private Long id;
    private String name;
    private String email;
    private Boolean confirmation;
    private Integer guestsAmount;
    private String message;
    private LocalDateTime replyDate;
    private Boolean approvedMessage;

}
