package com.ikytus.prysma.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ikytus.prysma.domain.Cliente;
import com.ikytus.prysma.domain.Empresa;
import com.ikytus.prysma.domain.Endereco;
import com.ikytus.prysma.domain.User;
import com.ikytus.prysma.domain.enums.ProfileEnum;
import com.ikytus.prysma.dto.EmpresaDTO;
import com.ikytus.prysma.repository.ClienteRepository;
import com.ikytus.prysma.repository.EmpresaRepository;
import com.ikytus.prysma.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		empresaRepository.deleteAll();
		clienteRepository.deleteAll();
		
		Endereco ender = new Endereco("61648-050", "Av. de Contorno Oeste", "Bloco 30 Apto 22A", "Nova Metrópole", "Caucaia", "CE", "405");
		
		Empresa emp1 = new Empresa(null, "Ikytus Sistemas", "03.729.627/0001-76", ender, "(85)988951038", "ikytussistemas@gmail.com", "Matriz", new EmpresaDTO(), "");
		Empresa emp2 = new Empresa(null, "Teste Sistemas", "03.729.627/0001-76", ender, "(85)988951038", "testesistemas@gmail.com", "Matriz", new EmpresaDTO(), "");
	
		empresaRepository.saveAll(Arrays.asList(emp1, emp2));
		
		Empresa emp3 = new Empresa(null, "Teste filial_1", "03.729.627/0001-76", ender, "(85)988951038", "testesistemas@gmail.com", "Filial", new EmpresaDTO(emp1), "");
		Empresa emp4 = new Empresa(null, "Teste filial_2", "03.729.627/0001-76", ender, "(85)988951038", "testesistemas@gmail.com", "Filial", new EmpresaDTO(emp1), "");
		Empresa emp5 = new Empresa(null, "Teste filial_3", "03.729.627/0001-76", ender, "(85)988951038", "testesistemas@gmail.com", "Filial", new EmpresaDTO(emp2), "");
		
		empresaRepository.saveAll(Arrays.asList(emp3, emp4, emp5));
		
		User franze = new User(null, "Francisco José", "franze@gmail.com",passwordEncoder.encode("123456"),ProfileEnum.ROLE_ADMIN,true, ender,new EmpresaDTO(emp1),"");
		
		User paulo = new User(null, "Paulo José", "paulo@gmail.com",passwordEncoder.encode("123456"),ProfileEnum.ROLE_CUSTOMER,true, ender,new EmpresaDTO(emp2),"");
						
		userRepository.saveAll(Arrays.asList(franze, paulo));
		
		Cliente cli1 = new Cliente(null, "cliente 1", "", "", sdf.parse("02/05/1979"), "", "", ender,  new EmpresaDTO(emp1));
		Cliente cli2 = new Cliente(null, "cliente 2", "61649864353", "2000010083139", sdf.parse("02/05/1979"), "88951038", "teste@teste.com", ender,  new EmpresaDTO(emp2));
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		
		emp1.getClientes().addAll(Arrays.asList(cli1));
		emp2.getClientes().addAll(Arrays.asList(cli2));
		
		empresaRepository.saveAll(Arrays.asList(emp1, emp2));
	}
}
