package com.anacarolinaebruno.wedding.api.controller;

import com.anacarolinaebruno.wedding.api.dto.request.RsvpRequestDTO;
import com.anacarolinaebruno.wedding.api.mapper.RsvpMapper;
import com.anacarolinaebruno.wedding.api.model.entity.RSVP;
import com.anacarolinaebruno.wedding.api.service.RsvpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rsvps")
@RequiredArgsConstructor
public class RsvpController {

    private final RsvpService rsvpService;
    private final RsvpMapper rsvpMapper;

    @PostMapping
    public ResponseEntity<?> createRsvp(@Valid @RequestBody RsvpRequestDTO rsvpRequestDTO) {
        RSVP createdRSVP = rsvpService.saveRsvp(rsvpMapper.toEntity(rsvpRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRSVP);
    }
}
