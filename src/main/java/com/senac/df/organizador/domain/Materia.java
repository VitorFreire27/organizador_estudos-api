package com.senac.df.organizador.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Component
@Entity
public class Materia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
//	@NotEmpty(message= "Campo NOME é requerido")
//	@Length(min= 3, max= 50, message= "Campo NOME deve ter entre 3 e 50 caracteres")
	private String name;
	@OneToMany(mappedBy = "materia")
	private List<Conteudo> Conteudos = new ArrayList<>();

	public Materia() {
		super();
	}

	public Materia(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	public List<Conteudo> getConteudos() {
		return Conteudos;
	}

	public void setConteudos(List<Conteudo> conteudos) {
		Conteudos = conteudos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Materia other = (Materia) obj;
		return id == other.id;
	}

}
