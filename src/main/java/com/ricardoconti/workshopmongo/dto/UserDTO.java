package com.ricardoconti.workshopmongo.dto;

import java.io.Serializable;

import com.ricardoconti.workshopmongo.domain.User;

// A classe UserDTO que vem de DTO (Data Transfer Object) é um objeto que tem o papel
// de carregar dados das entidades de forma simples, podendo inclusive "projetar"
// apenas alguns dados da entidade original. 

public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private String id;
	private  String name;
	private String email;

	public UserDTO() {
		
	}
	
	//Acesso aos atributos da classe User a partir do objeto
	public UserDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
	
	
	
}
