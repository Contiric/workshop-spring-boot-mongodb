package com.ricardoconti.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ricardoconti.workshopmongo.domain.Post;

@Repository
//Essa interface vai herdar da interface do Mongo
//User - O tipo da classe domínio que o Mongo vai gerenciar
//String id - Identificando o tipo do Id da classe de domínio User
public interface PostRepository extends MongoRepository<Post, String> {
	

	List<Post> findByTitleContainingIgnoreCase(String text);
	
	@Query("{'title': {$regex: ?0, $options: 'i' } }")
	List <Post> searchTitle (String text);
	
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch (String text, Date minDate, Date maxDate);
}