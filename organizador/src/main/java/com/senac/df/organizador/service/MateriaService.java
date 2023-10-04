package com.senac.df.organizador.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.senac.df.organizador.domain.Materia;
import com.senac.df.organizador.dtos.MateriaDTO;
import com.senac.df.organizador.exceptions.ObjectNotFoundException;
import com.senac.df.organizador.repositories.MateriaRepository;

@Service
public class MateriaService {

	@Autowired
	private MateriaRepository repository;

	public Materia findById(Integer id) {
		Optional<Materia> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Materia.class.getName()));
	}

	public List<Materia> findAll() {
		return repository.findAll();
	}

	public Materia create(Materia obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Materia update(Integer id, MateriaDTO objDto) {
		Materia obj = findById(id);
		obj.setName(objDto.getName());
		return repository.save(obj);

	}

	public void delete(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new com.senac.df.organizador.resources.exceptions.DataIntegrityViolationException(
					"Matéria não pode ser deletado! Posue conteúdos associados.");
		}

	}

}
