package com.ikytus.prysma.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ikytus.prysma.domain.Empresa;
import com.ikytus.prysma.domain.User;
import com.ikytus.prysma.domain.enums.ProfileEnum;
import com.ikytus.prysma.domain.models.Response;
import com.ikytus.prysma.dto.EmpresaDTO;
import com.ikytus.prysma.services.EmpresaService;
import com.ikytus.prysma.services.UserService;

@RestController
@RequestMapping(value="/users")
@CrossOrigin(origins="*")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping("/{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN_SISTEMA')")
    public  ResponseEntity<Response<Page<User>>> findAll(@PathVariable int page, @PathVariable int count) {
		Response<Page<User>> response = new Response<Page<User>>();
		Page<User> users = service.findAll(page, count);
		response.setData(users);
		return ResponseEntity.ok(response);
    }
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN_EMPRESA')")
	public ResponseEntity<Response<User>> findId(@PathVariable String id){
		Response<User> response = new Response<User>();
		User user = service.findById(id);
		if (user == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(user);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<Response<User>> create(HttpServletRequest request, @RequestBody User user, BindingResult result){
		Response<User> response = new Response<User>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()) );
			return ResponseEntity.badRequest().body(response);
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User userPersistend = service.createOrUpdate(user);
		response.setData(userPersistend);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userPersistend.getId()).toUri();
		return ResponseEntity.created(uri).body(response);
	}
	
	@PutMapping()
	public ResponseEntity<Response<User>> update(HttpServletRequest request, @RequestBody User user, BindingResult result){
		Response<User> response = new Response<User>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()) );
			return ResponseEntity.badRequest().body(response);
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User userPersistend = service.createOrUpdate(user);
		response.setData(userPersistend);
		return ResponseEntity.ok(response);
	}
	
	@PatchMapping("/{status}/{id}")
	public ResponseEntity<Void> updateStatus(
			@PathVariable("status") String status, 
			@PathVariable("id") String id, BindingResult result){
		
		System.out.println("Status na api: " + status);
		service.updateStatus(status, id);
		return ResponseEntity.noContent().build();
	}
			
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id) {
		Response<String> response = new Response<String>();
		User user = service.findById(id);
		if (user == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		service.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	@GetMapping("/{id}/empresas")
	public ResponseEntity<List<EmpresaDTO>> findEmpresas(@PathVariable String id){
		User user = service.findById(id);
		List<Empresa> list= new ArrayList<>();
		
		if(user.getProfile().contains(ProfileEnum.ADMIN_SISTEMA)) {
			list = empresaService.findAll();
		}else {
			String idEmpresa = service.findById(id).getEmpresa().getId();
			list = service.empresasFindByUser(idEmpresa);
		}
		List<EmpresaDTO> listDTO = list.stream().map(x -> new EmpresaDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
}