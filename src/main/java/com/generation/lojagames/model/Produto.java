package com.generation.lojagames.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo texto é Obrigatório!")
	@Size(min = 3, max = 255, message = "O atributo nome deve ter no minimo 3 e no maximo 255 caracteres")
	private String nome;
	
	@NotBlank(message = "O atributo descricao é Obrigatório!")
	@Size(min = 3, max = 1000, message = "O atributo descricao deve ter no minimo 3 e no maximo 1000 caracteres")
	private String descricao;
	
	@NotBlank(message = "O atributo console é Obrigatório!")
	@Size(min = 2, max = 255, message = "O atributo console deve ter no minimo 2 e no maximo 255 caracteres")
	private String console;
	
	@NotBlank(message = "O atributo quantidade é Obrigatório!")
	private Integer quantidade;
	
	@NotBlank(message = "O atributo data Lancamento é Obrigatório!")
	private LocalDateTime dataLancamento;
	
	@NotBlank(message = "O atributo preço é Obrigatório!")
	private Integer preco;
	
	private String foto;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public LocalDateTime getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDateTime dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Integer getPreco() {
		return preco;
	}

	public void setPreco(Integer preco) {
		this.preco = preco;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	/*Criar os metodos get e set do objeto tema*/
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Categoria categoria;
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setTema(Categoria categoria) {
		this.categoria = categoria;
	}
}
