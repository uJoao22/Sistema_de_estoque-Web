package br.com.eletroandrade.sistemaestoque.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {
	private static final long serialVersionUID = 3375655130283775015L;
	
	private Long codigo;
	private String nome;
	private Categoria categoria;
	private double preco;
	private int quantidade;
	private String marca;
	private String modelo;
	private String adicionais;
	
	public Produto() {
	}
	
	public Produto(Long codigo) {
		this.codigo = codigo;
	}

	@Id
	@Column(name = "codigo_produto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@NotNull
	@Size(max = 100)
	@Column(name = "nome", nullable = false, length = 100)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotNull
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria", nullable = false)
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@NotNull
	@Range(min=0)
	@Column(name = "preco", columnDefinition="numeric(10,2) default 0.00", nullable = false)
	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@NotNull
	@Column(name = "quantidade", nullable = false, columnDefinition = "int4 default 0")
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Size(max = 30)
	@Column(name = "marca", length = 30)
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Size(max = 100)
	@Column(name = "modelo", length = 100)
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Column(name = "adicionais")
	@Type(type = "org.hibernate.type.TextType")
	public String getAdicionais() {
		return adicionais;
	}

	public void setAdicionais(String adicionais) {
		this.adicionais = adicionais;
	}
	
}
