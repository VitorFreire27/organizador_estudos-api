package com.senac.df.organizador.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.senac.df.organizador.domain.Materia;
import com.senac.df.organizador.dtos.MateriaDTO;
import com.senac.df.organizador.service.MateriaService;

import jakarta.validation.Valid;

//@CrossOrigin("*")
@RestController
@RequestMapping(value = "/materia")
public class MateriaResource {

	@Autowired
	private MateriaService service;

	@GetMapping(value = "{id}")
	public ResponseEntity<Materia> findById(@PathVariable Integer id) {
		Materia obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<MateriaDTO>> findAll() {
		List<Materia> list = service.findAll();
		List<MateriaDTO> listDTO = list.stream().map(obj -> new MateriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<Materia> create(@Valid @RequestBody Materia obj) {
		obj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<MateriaDTO> update(@Valid @PathVariable Integer id, @RequestBody MateriaDTO objDto) {
		Materia newObj = service.update(id, objDto);
		return ResponseEntity.ok().body(new MateriaDTO(newObj));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> detele(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
