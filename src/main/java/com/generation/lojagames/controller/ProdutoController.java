package com.generation.lojagames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.lojagames.model.Produto;
import com.generation.lojagames.repository.CategoriaRepository;
import com.generation.lojagames.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

		@Autowired
		private ProdutoRepository produtoRepository;
		
		@Autowired
		private CategoriaRepository categoriaRepository;
		
		@GetMapping
		public ResponseEntity<List<Produto>> getAll(){
			return ResponseEntity.ok(produtoRepository.findAll());
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<Produto> getById(@PathVariable Long id) {
		    return produtoRepository.findById(id)
		            .map(produto -> ResponseEntity.ok(produto))
		            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		}
		
		@GetMapping("/nome/{nome}")
		public ResponseEntity<List<Produto>> getByTitulo(@PathVariable String nome) {
		    List<Produto> produto = produtoRepository.findAllByNomeContainingIgnoreCase(nome);
		    /*IF ternario*/
		    return produto.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.status(HttpStatus.OK).build();
		}
		
		@PostMapping
		public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {
		    if (!categoriaRepository.existsById(produto.getId())) {
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		    }
		    return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
		}
		
		@PutMapping
		public ResponseEntity<?> put(@Valid @RequestBody Produto produto){
		    if (!categoriaRepository.existsById(produto.getId())) {
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		    }
		    return produtoRepository.findById(produto.getId())
		            .map(resposta -> {
		            	produtoRepository.save(produto);
		                return ResponseEntity.status(HttpStatus.OK).build();
		            })
		            .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
		}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<?> deleteById(@PathVariable Long id) { 
			return produtoRepository.findById(id)
					.map(resposta -> {
						produtoRepository.deleteById(id);
						return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
					})
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

		}
}