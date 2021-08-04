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

		
		postRepository.saveAll(Arrays.asList(post1, post2));
	}

}
