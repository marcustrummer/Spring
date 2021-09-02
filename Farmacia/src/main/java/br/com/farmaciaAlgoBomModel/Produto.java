package br.com.farmaciaAlgoBomModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Produto { // atributos do produto: ID, NOME, PRECO, QTD
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "O atributo nome eh obgt")
	@Size(message = "O atbt deve conter entre 4 e 100 caracteres", min = 4, max = 100)
	private String nome;
	
	@NotNull(message = "O atributo preco eh obgt")
	private double preco;
	
	@NotNull(message = "O atributo qtd eh obgt")
	private double qtd;

	
	//----------------------------RELACIONAMENTOS--------------------------------------
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;
	
	
	

	
	//--------------------------GETTERS / SETTERS --------------------------------------------------
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public double getQtd() {
		return qtd;
	}

	public void setQtd(double qtd) {
		this.qtd = qtd;
	}
	
	

}
