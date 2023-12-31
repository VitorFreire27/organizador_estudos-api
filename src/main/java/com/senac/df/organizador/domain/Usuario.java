package com.senac.df.organizador.domain;

import java.util.Objects;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Component
@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
//	@NotEmpty(message = "Campo NOME é requerido")
//	@Length(min = 3, max = 60, message = "Campo NOME deve ter entre 2 e 60 caracteres")
	private String name;
//	@NotEmpty(message = "Campo E-MAIL é requerido")
//	@Length(min = 7, max = 50, message = "Campo E-MAIL deve ter entre 7 e 50 caracteres")
	private String email;
//	@NotEmpty(message = "Campo SENHA é requerido")
//	@Length(min = 8, max = 50, message = "Campo NOME deve ter no mínino 8 caracteres")
//	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", message = "A senha deve conter pelo menos uma letra maiúscula, "
//			+ "uma letra minúscula, um número e um caractere especial.")
	private String senha;

	public Usuario() {
		super();
	}

	public Usuario(Integer id, String name, String email, String senha) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.senha = senha;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

}
