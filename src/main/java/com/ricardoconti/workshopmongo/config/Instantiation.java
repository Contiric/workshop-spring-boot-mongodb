package com.ricardoconti.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ricardoconti.workshopmongo.domain.User;
import com.ricardoconti.workshopmongo.repository.UserRepository;

// Essa classe é responsável por inserir automaticamente dados no banco 
// Ela vai instanciar a classe UserRepository para poder acessar os métodos delete e save
// A partir da instanciação da classe User podemos inserir os respectivos dados 

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
	}

}
