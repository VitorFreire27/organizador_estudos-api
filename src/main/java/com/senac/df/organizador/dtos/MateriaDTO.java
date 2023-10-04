package com.senac.df.organizador.dtos;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.senac.df.organizador.domain.Materia;

import jakarta.validation.constraints.NotEmpty;

public class MateriaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message= "Campo NOME é requerido")
	@Length(min= 2, max= 50, message= "Campo NOME deve ter entre 3 e 50 caracteres")
	private String name;

	public MateriaDTO() {
		super();
	}

	public MateriaDTO(Materia obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
