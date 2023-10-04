package com.senac.df.organizador.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.df.organizador.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
