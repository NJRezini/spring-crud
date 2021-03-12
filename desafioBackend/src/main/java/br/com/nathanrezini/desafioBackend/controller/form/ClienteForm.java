package br.com.nathanrezini.desafioBackend.controller.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.nathanrezini.desafioBackend.model.Cidade;
import br.com.nathanrezini.desafioBackend.model.Cliente;
import br.com.nathanrezini.desafioBackend.repository.CidadeRepository;

public class ClienteForm {

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String nome;
	@NotNull
	@NotEmpty
	private String sexo;
	@NotNull
	private Date dataNascimento;
	@NotNull
	@NotEmpty
	private String nomeCidade;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public Cliente converter(CidadeRepository cidadeRepository) {
		Cidade cidade = cidadeRepository.findByNome(nomeCidade);
		return new Cliente(nome, sexo, dataNascimento, cidade);
	}
}
