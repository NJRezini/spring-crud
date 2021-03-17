package br.com.nathanrezini.desafioBackend.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.nathanrezini.desafioBackend.controller.dto.CidadeDto;
import br.com.nathanrezini.desafioBackend.controller.form.CidadeForm;
import br.com.nathanrezini.desafioBackend.model.Cidade;
import br.com.nathanrezini.desafioBackend.repository.CidadeRepository;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@GetMapping
	@Cacheable(value = "listaDeCidades")
	public Page<CidadeDto> lista(@RequestParam(required = false) String nomeCidade,
			@RequestParam(required = false) String nomeEstado,
			@PageableDefault(sort = "nome", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {

		if (nomeCidade != null) {
			Page<Cidade> cidades = cidadeRepository.findByNome(nomeCidade, paginacao);
			return CidadeDto.converter(cidades);
		} else if (nomeEstado != null) {
			Page<Cidade> cidades = cidadeRepository.findByEstado(nomeEstado, paginacao);
			return CidadeDto.converter(cidades);
		} else {
			Page<Cidade> cidades = cidadeRepository.findAll(paginacao);
			return CidadeDto.converter(cidades);
		}
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDeCidades", allEntries = true)
	public ResponseEntity<CidadeDto> cadastrar(@RequestBody @Valid CidadeForm form, UriComponentsBuilder uriBuilder) {
		Cidade cidade = form.converter();
		cidadeRepository.save(cidade);

		URI uri = uriBuilder.path("/cidade/{id}").buildAndExpand(cidade.getId()).toUri();
		return ResponseEntity.created(uri).body(new CidadeDto(cidade));
	}

}
