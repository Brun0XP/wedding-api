package com.anacarolinaebruno.wedding.api.repository;

import com.anacarolinaebruno.wedding.api.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByLogin(String login);

}
