package com.ricardoconti.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ricardoconti.workshopmongo.domain.User;
import com.ricardoconti.workshopmongo.dto.UserDTO;
import com.ricardoconti.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET)
	//@GetMapping
	public ResponseEntity <List<UserDTO>> findAll() {
		//User maria = new User("1", "Maria Silva", "maria@gmail.com");
		//User alex = new User("1", "Alex Pinheiro", "alex@gmail.com");

		List<User> list = service.findAll();
		//list.addAll(Arrays.asList(maria, alex));
		
		//Conversão da Lista de User para UserDTO
		//Transformar a lista original em uma stream
		//Chamar método map, que vai pegar cada obj x da lista original 
		//Para cada obj que vai ser um usuário vou retornar o new DTO passando o x como argumento
		//Voltar o stream para uma lista 
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		//Vai retornar uma listaDTO
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity <UserDTO> findById(@PathVariable String id) {
		
		User obj = service.findById(id);

	
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	
}
