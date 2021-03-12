package br.com.nathanrezini.dasafioBackend.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.nathanrezini.dasafioBackend.model.Cidade;

public class CidadeForm {

	@NotNull @NotEmpty @Length(min = 5)
	private String nome;
	@NotNull @NotEmpty @Length(min = 2, max = 2)
	private String estado;
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Cidade converter() {
		return new Cidade(nome, estado);
	}
}
