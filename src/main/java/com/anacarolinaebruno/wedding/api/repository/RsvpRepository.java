package com.anacarolinaebruno.wedding.api.repository;

import com.anacarolinaebruno.wedding.api.model.entity.RSVP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RsvpRepository extends JpaRepository<RSVP, Long> {

    List<RSVP> findAllByApprovedMessageTrueOrderByReplyDateDesc();

    Optional<RSVP> findByEmail(String email);
}
