package com.ikytus.prysma.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.ikytus.prysma.dto.EmpresaDTO;
import com.ikytus.prysma.services.EmpresaService;

@RestController
@RequestMapping(value="/empresas")
public class EmpresaResource {
	
	@Autowired
	private EmpresaService service;
	
	@GetMapping
	public ResponseEntity<List<EmpresaDTO>> findAll(){
		List<Empresa> list = service.findAll();
		List<EmpresaDTO> listDTO = list.stream().map(x -> new EmpresaDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmpresaDTO> findId(@PathVariable String id){
		Empresa empresa = service.findById(id);
		return ResponseEntity.ok().body(new EmpresaDTO(empresa));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Empresa empresa){
		//Empresa empresa = service.fromDTO(objDto);
		empresa = service.insert(empresa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(empresa.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody Empresa empresa, @PathVariable String id){
		//Empresa empresa = service.fromDTO(objDto);
		empresa.setId(id);
		empresa = service.update(empresa);
		return ResponseEntity.noContent().build();
	}
}
