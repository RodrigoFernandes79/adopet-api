package com.adopet.apiadopet.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adopet.apiadopet.domains.tutor.Tutor;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {

	Optional<Tutor> findByEmail(String email);

}
