package br.com.nathanrezini.desafioBackend.controller.dto;

import java.util.Date;

import org.springframework.data.domain.Page;

import br.com.nathanrezini.desafioBackend.model.Cidade;
import br.com.nathanrezini.desafioBackend.model.Cliente;

public class ClienteDto {

	private Long id;
	private String nome;
	private String sexo;
	private Date dataNascimento;
	private Cidade cidade;

	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.sexo = cliente.getSexo();
		this.dataNascimento = cliente.getDataNascimento();
		this.cidade = cliente.getCidade();
	}

	public Long getId() {
		return id;
	}

	public String getNomeCompleto() {
		return nome;
	}

	public String getSexo() {
		return sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public static Page<ClienteDto> converter(Page<Cliente> cliente) {
		return cliente.map(ClienteDto::new);
	}
}
