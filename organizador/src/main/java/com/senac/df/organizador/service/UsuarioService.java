package com.senac.df.organizador.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senac.df.organizador.domain.Usuario;
import com.senac.df.organizador.exceptions.ObjectNotFoundException;
import com.senac.df.organizador.repositories.UsuarioRepository;
import com.senac.df.organizador.resources.exceptions.EmailDuplicadoException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}

	public Usuario findById(Integer id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}

	public List<Usuario> findAll() {
		return repository.findAll();
	}

	public Integer getUserIdByEmail(String email) {
		return findAll().stream().filter(u -> u.getEmail().equals(email)).map(Usuario::getId).findFirst().orElse(null);
	}

	public Usuario create(Usuario obj) {

		if (emailAlreadyExists(obj.getEmail())) {
			throw new EmailDuplicadoException("E-mail já existe. Tente outro.");
		}
		obj.setId(null);
		return repository.save(obj);

	}

	public boolean emailAlreadyExists(String email) {
		return findAll().stream().anyMatch(u -> u.getEmail().equals(email));
	}

	public boolean senhaAlreadyExists(String senha) {
		return findAll().stream().anyMatch(u -> u.getSenha().equals(senha));

	}

	public Usuario update(Integer id, Usuario obj) {
		Usuario old = findById(id);
		old.setEmail(obj.getEmail());
		old.setSenha(obj.getSenha());
		return repository.save(old);
	}

	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);

	}

}