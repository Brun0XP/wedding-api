package com.anacarolinaebruno.wedding.api.controller;

import com.anacarolinaebruno.wedding.api.dto.request.RsvpRequestDTO;
import com.anacarolinaebruno.wedding.api.mapper.RsvpMapper;
import com.anacarolinaebruno.wedding.api.model.entity.RSVP;
import com.anacarolinaebruno.wedding.api.service.RsvpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("rsvps")
@RequiredArgsConstructor
public class RsvpController {

    private final RsvpService rsvpService;
    private final RsvpMapper rsvpMapper;

    @PostMapping
    public ResponseEntity<?> createRsvp(@Valid @RequestBody RsvpRequestDTO rsvpRequestDTO, UriComponentsBuilder uriBuilder) {
        RSVP rsvp = rsvpService.saveRsvp(rsvpMapper.toEntity(rsvpRequestDTO));
        return ResponseEntity
                .created(uriBuilder.path("/rsvps/{id}").buildAndExpand(rsvp.getId()).toUri())
                .body(rsvpMapper.toResponseDTO(rsvp));
    }
}
