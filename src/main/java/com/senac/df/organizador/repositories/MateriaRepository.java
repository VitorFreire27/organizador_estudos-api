package com.senac.df.organizador.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.df.organizador.domain.Materia;

public interface MateriaRepository extends JpaRepository<Materia, Integer> {

}
