package com.ricardoconti.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ricardoconti.workshopmongo.domain.Post;

@Repository
//Essa interface vai herdar da interface do Mongo
//User - O tipo da classe domínio que o Mongo vai gerenciar
//String id - Identificando o tipo do Id da classe de domínio User
public interface PostRepository extends MongoRepository<Post, String> {
	
}