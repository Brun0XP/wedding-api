package com.anacarolinaebruno.wedding.api.service;

import com.anacarolinaebruno.wedding.api.model.entity.RSVP;
import com.anacarolinaebruno.wedding.api.repository.RsvpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RsvpService {

    private final RsvpRepository rsvpRepository;

    public RSVP saveRsvp(RSVP rsvp) {
        if (rsvpRepository.findByEmail(rsvp.getEmail()).isPresent())
            throw new IllegalArgumentException("Você já respondeu este formulário!");

        return rsvpRepository.save(rsvp);
    }

    public List<RSVP> getRsvpMessagesApproved() {
        return rsvpRepository.findAllByApprovedMessageTrueOrderByReplyDateDesc();
    }

}
