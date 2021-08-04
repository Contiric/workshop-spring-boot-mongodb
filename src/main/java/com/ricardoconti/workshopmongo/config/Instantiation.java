package com.ricardoconti.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ricardoconti.workshopmongo.domain.Post;
import com.ricardoconti.workshopmongo.domain.User;
import com.ricardoconti.workshopmongo.dto.AuthorDTO;
import com.ricardoconti.workshopmongo.dto.CommentDTO;
import com.ricardoconti.workshopmongo.repository.PostRepository;
import com.ricardoconti.workshopmongo.repository.UserRepository;

// Essa classe é responsável por inserir automaticamente dados no banco 
// Ela vai instanciar a classe UserRepository para poder acessar os métodos delete e save
// A partir da instanciação da classe User podemos inserir os respectivos dados 

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem", "Vou viajar para Portugal. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Bora tomar café!", new AuthorDTO(maria));

		CommentDTO c1 = new CommentDTO("Boa viagem bro", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveita e traz uma camisa do Benfica", sdf.parse("21/03/2018"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Ótimo dia para você", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO c4 = new CommentDTO("Boa viagem bro", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2, c4));
		post2.getComments().addAll(Arrays.asList(c3));
		
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
