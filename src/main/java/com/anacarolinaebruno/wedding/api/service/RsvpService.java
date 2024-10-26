package com.anacarolinaebruno.wedding.api.service;

import com.anacarolinaebruno.wedding.api.model.entity.RSVP;
import com.anacarolinaebruno.wedding.api.repository.RsvpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RsvpService {

    private final RsvpRepository rsvpRepository;

    public RSVP saveRsvp(RSVP rsvp) {
        return rsvpRepository.save(rsvp);
    }

    public List<RSVP> getRsvpMessagesApproved() {
        return rsvpRepository.findAllByApprovedMessageTrueOrderByReplyDateDesc();
    }

}
