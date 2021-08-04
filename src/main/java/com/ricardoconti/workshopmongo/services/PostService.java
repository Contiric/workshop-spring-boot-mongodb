package com.ricardoconti.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricardoconti.workshopmongo.domain.Post;
import com.ricardoconti.workshopmongo.repository.PostRepository;
import com.ricardoconti.workshopmongo.services.exception.ObjectNotFoundExcepetion;

@Service
//Serviço responsável por trabalhar com usuários
public class PostService {
	
	//Para realizar a listagem dos usuários, a camada de serviço tem que conversar com o repository
	//A anotação serve para instanciar automaticamente o objeto no serviço, injeção de depedencia
	@Autowired
	private PostRepository repo;
	

	public Post findById (String id) {
		Optional<Post> user = repo.findById(id);
		return user.orElseThrow(() ->  new ObjectNotFoundExcepetion("Objeto não encontrado"));
	}
	

	public List<Post> findByTitle(String text) {
		return repo.searchTitle(text);
	}

}
