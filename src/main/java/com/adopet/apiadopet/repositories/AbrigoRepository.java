package com.adopet.apiadopet.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adopet.apiadopet.domains.abrigo.Abrigo;

@Repository
public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {

	Optional<Abrigo> findByCnpj(String cnpjAbrigo);

	Optional<Abrigo> findByEmail(String email);

}
