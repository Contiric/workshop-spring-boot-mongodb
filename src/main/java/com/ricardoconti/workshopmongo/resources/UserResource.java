package com.ricardoconti.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ricardoconti.workshopmongo.domain.Post;
import com.ricardoconti.workshopmongo.domain.User;
import com.ricardoconti.workshopmongo.dto.UserDTO;
import com.ricardoconti.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET)
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
	
	@PostMapping
	public ResponseEntity <Void> insert(@RequestBody UserDTO objDTO){
		
		User obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity <Void> delete(@PathVariable String id) {
		 
		service.delete(id);
	
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping (value = "/{id}")
	public ResponseEntity <Void> insert(@RequestBody UserDTO objDTO, @PathVariable String id){
		
		User obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();

	}
	
	@RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
	public ResponseEntity <List<Post>> findPosts(@PathVariable String id) {
		
		User obj = service.findById(id);

	
		return ResponseEntity.ok().body(obj.getPosts());
	}
	
	
	
	
}
