package com.jamal.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jamal.cursomc.domain.Categoria;
import com.jamal.cursomc.services.CategoriaService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
		// A operação "save" retorna o objeto salvo, por isso o "obj = ..."
		obj = service.insert(obj);

		// Criando URI para ser retornado junto ao HTTP code ("/categorias/{id}")
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		// Retorna o status code 201 + URI gerado
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id) {
		// Seta o ID do parametro, apenas para garantir que está sendo atualizado o mesmo objeto
		obj.setId(id);

		obj = service.update(obj);

		// Retorna um response vazio
		return ResponseEntity.noContent().build();
	}
}
