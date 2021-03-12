package br.com.nathanrezini.dasafioBackend.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.nathanrezini.dasafioBackend.model.Cliente;
import br.com.nathanrezini.dasafioBackend.repository.ClienteRepository;

public class AtualizacaoClienteForm {

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String nome;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cliente atualizar(Long id, ClienteRepository clienteRepository) {
		Cliente cliente = clienteRepository.getOne(id);

		cliente.setNome(this.nome);

		return cliente;
	}
}
