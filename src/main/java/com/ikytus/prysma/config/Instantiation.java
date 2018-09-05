package com.ikytus.prysma.config;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ikytus.prysma.repository.PostRepository;
import com.ikytus.prysma.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

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
		
		/*User franze = new User(null, "Francisco José", "franze@gmail.com");
		User paulo = new User(null, "Paulo André", "paulo@gmail.com");
		User bob = new User(null, "Bob dim", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(franze, paulo,bob));
		
		Post post1 = new Post(null,sdf.parse("21/03/2018"), "Partiu Viagem","Vou viajar para São Paulo. Abraços!", new AuthorDTO(franze));
		Post post2 = new Post(null,sdf.parse("23/03/2018"), "Bom Dia","Acordei feliz hoje!", new AuthorDTO(franze));
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!",sdf.parse("21/03/2018"), new AuthorDTO(paulo));
		CommentDTO c2 = new CommentDTO("Aproveite!",sdf.parse("22/03/2018"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!",sdf.parse("23/03/2018"), new AuthorDTO(paulo));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		franze.getPosts().addAll(Arrays.asList(post1, post2));
		
		userRepository.save(franze);*/
		
	}

}
