package com.anacarolinaebruno.wedding.api.controller;

import com.anacarolinaebruno.wedding.api.dto.request.AuthenticationRequest;
import com.anacarolinaebruno.wedding.api.dto.response.AuthenticationResponse;
import com.anacarolinaebruno.wedding.api.model.entity.User;
import com.anacarolinaebruno.wedding.api.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> doLogin(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authenticationRequest.login(), authenticationRequest.password());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        String jwtToken = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }
}
