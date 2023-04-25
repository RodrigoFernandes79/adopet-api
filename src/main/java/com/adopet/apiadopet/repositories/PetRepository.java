package com.adopet.apiadopet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adopet.apiadopet.domains.pet.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}
