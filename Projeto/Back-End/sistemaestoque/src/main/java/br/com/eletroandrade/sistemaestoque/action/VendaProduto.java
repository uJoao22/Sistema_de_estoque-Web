package br.com.eletroandrade.sistemaestoque.action;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.eletroandrade.sistemaestoque.model.Produto;
import br.com.eletroandrade.sistemaestoque.model.Venda;

@Entity
@Table(name = "venda_produto")
public class VendaProduto implements Serializable {
	private static final long serialVersionUID = 8409455158356160628L;
	
	private Long id;
	@JsonBackReference
	private Venda venda;
	private Produto produto;
	private int quantidade;
	
	public VendaProduto() {
	}
	
	@Id
	@Column(name="id_venda_produto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "id_venda", referencedColumnName="id_venda")
	public Venda getVenda() {
		return venda;
	}
	
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "codigo_produto", referencedColumnName="codigo_produto")
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	@NotNull
	@Min(value=1)
	@Column(name="quantidade", columnDefinition = "int4 default 1")
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VendaProduto other = (VendaProduto) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}

	
}
