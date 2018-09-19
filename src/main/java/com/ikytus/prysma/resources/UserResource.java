package com.ikytus.prysma.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ikytus.prysma.domain.Empresa;
import com.ikytus.prysma.domain.Response;
import com.ikytus.prysma.domain.User;
import com.ikytus.prysma.domain.enums.ProfileEnum;
import com.ikytus.prysma.dto.EmpresaDTO;
import com.ikytus.prysma.dto.UserDTO;
import com.ikytus.prysma.services.EmpresaService;
import com.ikytus.prysma.services.UserService;

@RestController
@RequestMapping(value="/users")
@CrossOrigin(origins="*")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
    public  ResponseEntity<Response<Page<User>>> findAll(@PathVariable int page, @PathVariable int count) {
		Response<Page<User>> response = new Response<Page<User>>();
		Page<User> users = service.findAll(page, count);
		response.setData(users);
		return ResponseEntity.ok(response);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findId(@PathVariable String id){
		User user = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){
		User user = service.fromDTO(objDto);
		user = service.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id){
		User user = service.fromDTO(objDto);
		user.setId(id);
		user = service.update(user);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}/empresas")
	public ResponseEntity<List<EmpresaDTO>> findEmpresas(@PathVariable String id){
		User user = service.findById(id);
		List<Empresa> list= new ArrayList<>();
		
		if(user.getProfile().equals(ProfileEnum.ROLE_ADMIN)) {
			list = empresaService.findAll();
		}else {
			String idEmpresa = service.findById(id).getEmpresa().getId();
			list = service.empresasFindByUser(idEmpresa);
		}
		List<EmpresaDTO> listDTO = list.stream().map(x -> new EmpresaDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
}
