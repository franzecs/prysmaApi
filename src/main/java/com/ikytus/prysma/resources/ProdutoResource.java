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

import com.ikytus.prysma.domain.Produto;
import com.ikytus.prysma.domain.models.Response;
import com.ikytus.prysma.services.ProdutoService;
import com.ikytus.prysma.services.UserService;

@RestController
@RequestMapping(value="/produtos")
@CrossOrigin(origins="*")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<Response<Produto>> insert(HttpServletRequest request, @RequestBody Produto obj, BindingResult result){	
		obj.setEmpresa(userService.userFromRequest(request).getEmpresa());
		Produto produto = service.createOrUpdate(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="{id}")
	public ResponseEntity<Response<Produto>> update(@RequestBody Produto produto, @PathVariable String id){
		Response<Produto> response = new Response<Produto>();
		produto.setId(id);
		response.setData(service.update(produto));
		return ResponseEntity.ok(response);
	}
			
	@GetMapping(value="{page}/{count}")
    public  ResponseEntity<Response<Page<Produto>>> findAllByEmpresa(@PathVariable int page, @PathVariable int count, HttpServletRequest request) {
		Response<Page<Produto>> response = new Response<Page<Produto>>();
		Page<Produto> produtos = service.findByEmpresa(page, count, userService.userFromRequest(request).getEmpresa().getId());
		response.setData(produtos);
		return ResponseEntity.ok(response);
    }
			
	@GetMapping(value="/n/{nome}/{page}/{count}")
    public  ResponseEntity<Response<Page<Produto>>> findByNome(@PathVariable("nome") String nome, @PathVariable int page, @PathVariable int count, HttpServletRequest request) {
		Response<Page<Produto>> response = new Response<Page<Produto>>();
		Page<Produto> produtos = service.findByNome(page, count, nome, userService.userFromRequest(request).getEmpresa().getId());
		response.setData(produtos);
		return ResponseEntity.ok(response);
    }
	
	@GetMapping(value="{id}")
	public ResponseEntity<Response<Produto>> findById(@PathVariable("id") String id) {
		Response<Produto> response = new Response<Produto>();
		Produto produto = service.findById(id);
		if (produto == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(produto);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value="{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}