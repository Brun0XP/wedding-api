package com.anacarolinaebruno.wedding.api.mapper;

import com.anacarolinaebruno.wedding.api.dto.request.RsvpRequestDTO;
import com.anacarolinaebruno.wedding.api.model.entity.RSVP;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RsvpMapper {

    RSVP toEntity (RsvpRequestDTO rsvpRequestDTO);


}
