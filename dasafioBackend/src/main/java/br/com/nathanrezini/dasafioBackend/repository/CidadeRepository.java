package br.com.nathanrezini.dasafioBackend.repository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nathanrezini.dasafioBackend.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	Page<Cidade> findByNome(String nomeCidade, Pageable paginacao);
	
	Page<Cidade> findByEstado(String nomeEstado, Pageable paginacao);

	Cidade findByNome(String nomeCidade);
}
