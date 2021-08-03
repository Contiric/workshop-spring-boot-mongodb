package com.ricardoconti.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricardoconti.workshopmongo.domain.User;
import com.ricardoconti.workshopmongo.dto.UserDTO;
import com.ricardoconti.workshopmongo.repository.UserRepository;
import com.ricardoconti.workshopmongo.services.exception.ObjectNotFoundExcepetion;

@Service
//Serviço responsável por trabalhar com usuários
public class UserService {
	
	//Para realizar a listagem dos usuários, a camada de serviço tem que conversar com o repository
	//A anotação serve para instanciar automaticamente o objeto no serviço, injeção de depedencia
	@Autowired
	private UserRepository repo;
	
	//Método responsável por retornar todos os usuários do banco
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User findById (String id) {
		Optional<User> user = repo.findById(id);
		return user.orElseThrow(() ->  new ObjectNotFoundExcepetion("Objeto não encontrado"));
	}
	
	public User insert (User obj) {
		return repo.insert(obj);
	}
	
	public User fromDTO(UserDTO objDTO) {
		return new User (objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}

}
