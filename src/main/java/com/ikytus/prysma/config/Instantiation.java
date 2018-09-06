package com.ikytus.prysma.config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ikytus.prysma.domain.Empresa;
import com.ikytus.prysma.domain.Endereco;
import com.ikytus.prysma.domain.User;
import com.ikytus.prysma.dto.EmpresaDTO;
import com.ikytus.prysma.repository.EmpresaRepository;
import com.ikytus.prysma.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		empresaRepository.deleteAll();
		
		Endereco ender = new Endereco("61648-050", "Av. de Contorno Oeste", "Bloco 30 Apto 22A", "Nova Metrópole", "Caucaia", "CE", "405");
		
		Empresa emp1 = new Empresa(null, "Ikytus Sistemas", "03.729.627/0001-76", ender, "(85)988951038", "ikytussistemas@gmail.com", "Matriz", new EmpresaDTO(), "");
		Empresa emp2 = new Empresa(null, "Teste Sistemas", "03.729.627/0001-76", ender, "(85)988951038", "testesistemas@gmail.com", "Matriz", new EmpresaDTO(), "");

		empresaRepository.saveAll(Arrays.asList(emp1, emp2));
				
		User franze = new User(null, "Francisco José", "franze@gmail.com","123",new ArrayList<String>(),true, ender,new EmpresaDTO(emp1));
		User paulo = new User(null, "Paulo José", "paulo@gmail.com","123",new ArrayList<String>(),true, ender,new EmpresaDTO(emp2));
				
		userRepository.saveAll(Arrays.asList(franze, paulo));
	}
}
