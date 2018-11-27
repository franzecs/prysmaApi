package com.ikytus.prysma.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ikytus.prysma.domain.Empresa;
import com.ikytus.prysma.domain.User;
import com.ikytus.prysma.dto.UserDTO;
import com.ikytus.prysma.repository.EmpresaRepository;
import com.ikytus.prysma.repository.UserRepository;
import com.ikytus.prysma.services.EmpresaService;
import com.ikytus.prysma.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private EmpresaService empresaService;	
	
	@Autowired
    protected JWTUtil jwtTokenUtil;
	
	public Page<User> findAll(int page, int count) {
		Pageable pages = PageRequest.of(page, count);
		return this.userRepository.findAll(pages);
	}
	
	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}
	
	public User findById(String id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User createOrUpdate (User user) {
		return userRepository.save(user);
	}
				
	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
		
	public User update(User user) {
		User newUser = findById(user.getId());
		updateData(newUser, user);
		return userRepository.save(newUser);
	}
	
	public User updateStatus(String status, String id) {
		User newUser = findById(id);
				
		if(status.equals("true")) {
			newUser.setIsAtivo(true);
		}else {
			newUser.setIsAtivo(false);
		}
		
		return userRepository.save(newUser);
	}
	
	public User updateURLPerfil(String url, String id) {
		
		User newUser = findById(id);
		newUser.setUrl_perfil(url);
		return userRepository.save(newUser);
	}
	
	public void updateData(User newUser, User user) {
		if(user.getSenha() != null) {
			newUser.setSenha(user.getSenha());
		}
		newUser.setNome(user.getNome());
		newUser.setEmail(user.getEmail());
		newUser.setEmpresa(user.getEmpresa());
		newUser.setEndereco(user.getEndereco());
		newUser.setIsAtivo(user.getIsAtivo());
		newUser.setUrl_perfil(user.getUrl_perfil());
	}
	
	public User fromDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getNome(), userDto.getEmail(), "",
				userDto.getIsAtivo(), userDto.getEndereco(), userDto.getEmpresa(), userDto.getUrl_perfil());
	}
	
	public List<Empresa> empresasFindByUser(String id){
			List<Empresa> empresas = new ArrayList<>();
			empresas.add(empresaService.findById(id));
			empresas.addAll(empresaRepository.findByMatrizId(id));
		return empresas;
	}
	
	public User userFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String email = jwtTokenUtil.getUsername(token);
        return findByEmail(email);
    }
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}