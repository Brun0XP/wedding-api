package com.anacarolinaebruno.wedding.api.mapper;

import com.anacarolinaebruno.wedding.api.dto.request.RsvpRequestDTO;
import com.anacarolinaebruno.wedding.api.dto.response.RsvpMessagesResponseDTO;
import com.anacarolinaebruno.wedding.api.dto.response.RsvpResponseDTO;
import com.anacarolinaebruno.wedding.api.model.entity.RSVP;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RsvpMapper {

    @Mapping(target = "approvedMessage", constant = "false")
    RSVP toEntity(RsvpRequestDTO rsvpRequestDTO);

    RsvpResponseDTO toResponseDTO(RSVP rsvp);

    List<RsvpMessagesResponseDTO> toResponseMessageListDTO(List<RSVP> rsvp);

}
