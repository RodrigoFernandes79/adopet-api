package com.adopet.apiadopet.domains.tutor;

import java.util.ArrayList;
import java.util.List;

import com.adopet.apiadopet.domains.adocao.Adocao;
import com.adopet.apiadopet.domains.endereco.Endereco;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Tutor")
@Table(name = "tutores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tutor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String imagem;
	private String telefone;
	private String email;
	private String senha;

	@Embedded
	private Endereco endereco;
	@Column(columnDefinition = "TEXT")
	private String sobre;

	@OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Adocao> adocao = new ArrayList<>();

	public Tutor(DadosEntradaTutor dadosEntradaTutor) {
		this.nome = dadosEntradaTutor.nome();
		this.email = dadosEntradaTutor.email();
		this.senha = dadosEntradaTutor.senhaRepetida();

	}

	public void dadosAbrigoAtualizado(DadosAtualizacaoTutor dadosAtualizacaoTutor) {
		if (dadosAtualizacaoTutor.imagem() != null) {
			this.imagem = dadosAtualizacaoTutor.imagem();
		}
		if (dadosAtualizacaoTutor.telefone() != null) {
			this.telefone = dadosAtualizacaoTutor.telefone();
		}
		if (dadosAtualizacaoTutor.endereco() != null) {
			if (this.endereco == null) {
				this.endereco = new Endereco(dadosAtualizacaoTutor.endereco());
			}
			this.endereco.dadosEnderecoAtualizado(dadosAtualizacaoTutor.endereco());

		}
		if (dadosAtualizacaoTutor.sobre() != null) {
			this.sobre = dadosAtualizacaoTutor.sobre();
		}
	}

}
