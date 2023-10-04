package com.senac.df.organizador.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.df.organizador.domain.Conteudo;

public interface ConteudoRepository extends JpaRepository<Conteudo, Integer> {

}
