package com.adopet.apiadopet.domains.adocao;

import java.time.LocalDateTime;
import java.util.UUID;

import com.adopet.apiadopet.domains.pet.Pet;
import com.adopet.apiadopet.domains.tutor.Tutor;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "adocao")
@Entity(name = "Adocao")
public class Adocao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "animal_id")
	private Pet pet;

	@ManyToOne
	@JoinColumn(name = "tutor_id")
	private Tutor tutor;

	private LocalDateTime data;

}
