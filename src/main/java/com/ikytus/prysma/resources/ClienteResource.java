package com.ikytus.prysma.resources;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

import com.ikytus.prysma.domain.Cliente;
import com.ikytus.prysma.domain.Response;
import com.ikytus.prysma.services.ClienteService;

@RestController
@RequestMapping(value="/Clientes")
@CrossOrigin(origins="*")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
			
	@GetMapping(value="{page}/{count}")
    public  ResponseEntity<Response<Page<Cliente>>> findAll(@PathVariable int page, @PathVariable int count) {
		Response<Page<Cliente>> response = new Response<Page<Cliente>>();
		Page<Cliente> clientes = service.findAll(page, count);
		response.setData(clientes);
		return ResponseEntity.ok(response);
    }
	
	@GetMapping(value="{empresaId}/{page}/{count}")
    public  ResponseEntity<Response<Page<Cliente>>> findByEmpresa(@PathVariable String empresaId,@PathVariable int page, @PathVariable int count) {
		Response<Page<Cliente>> response = new Response<Page<Cliente>>();
		Page<Cliente> clientes = service.findByEmpresa(page, count, empresaId);
		response.setData(clientes);
		return ResponseEntity.ok(response);
    }
	
	@GetMapping(value="{empresaId}/{cpf}/{page}/{count}")
    public  ResponseEntity<Response<Page<Cliente>>> findByCpf(@PathVariable String empresaId, @PathVariable String cpf, @PathVariable int page, @PathVariable int count) {
		Response<Page<Cliente>> response = new Response<Page<Cliente>>();
		Page<Cliente> clientes = service.findByCpf(page, count, cpf, empresaId);
		response.setData(clientes);
		return ResponseEntity.ok(response);
    }
	
	@GetMapping(value="{id}")
	public ResponseEntity<Response<Cliente>> findById(@PathVariable("id") String id) {
		Response<Cliente> response = new Response<Cliente>();
		Cliente cliente = service.findById(id);
		if (cliente == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(cliente);
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping
	public ResponseEntity<Response<Cliente>> insert(HttpServletRequest request, @RequestBody Cliente obj, BindingResult result){
		Cliente cliente = service.createOrUpdate(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Response<Cliente>> update(@RequestBody Cliente cliente, @PathVariable String id){
		Response<Cliente> response = new Response<Cliente>();
		cliente.setId(id);
		response.setData(service.update(cliente));
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
