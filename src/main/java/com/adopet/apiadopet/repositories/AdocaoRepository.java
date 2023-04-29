package com.adopet.apiadopet.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adopet.apiadopet.domains.adocao.Adocao;

@Repository
public interface AdocaoRepository extends JpaRepository<Adocao, UUID> {

	Adocao findByPetId(Long id_pet);

}
