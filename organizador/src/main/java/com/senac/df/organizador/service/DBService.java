package com.senac.df.organizador.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senac.df.organizador.domain.Conteudo;
import com.senac.df.organizador.domain.Materia;
import com.senac.df.organizador.domain.Usuario;
import com.senac.df.organizador.repositories.ConteudoRepository;
import com.senac.df.organizador.repositories.MateriaRepository;
import com.senac.df.organizador.repositories.UsuarioRepository;

@Service
public class DBService {

	@Autowired
	private MateriaRepository materiaRepository;
	@Autowired
	private ConteudoRepository conteudoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	public void instanciaBaseDeDados() {
		Materia m = new Materia(null, "Estatística");
		Materia m1 = new Materia(null, "Matemática");

		Conteudo c = new Conteudo(null, "Álgebra Linear", m1);
		Conteudo c1 = new Conteudo(null, "Probabilidade", m);

		Usuario u = new Usuario(null, "vitormf27@gmail.com", "Senha123!");
		Usuario u1 = new Usuario(null, "davimf27@gmail.com", "Senha123!");

		m.getConteudos().addAll(Arrays.asList(c1));
		m1.getConteudos().addAll(Arrays.asList(c));

		this.materiaRepository.saveAll(Arrays.asList(m, m1));
		this.conteudoRepository.saveAll(Arrays.asList(c, c1));
		this.usuarioRepository.saveAll(Arrays.asList(u, u1));
	}
}
