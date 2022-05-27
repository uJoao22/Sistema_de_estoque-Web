package br.com.eletroandrade.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "vendas")
public class Venda {
	
	private Long id;
	private Long idPedido;
	private Produto produto;
	private int quantidade;
	private Date dataVenda;
	private String pagamento;
	
	public Venda() {
	}

	public Venda(Long id) {
		this.id = id;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull
	@Column(name = "id_pedido", nullable = false)
	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_produto", referencedColumnName = "codigo_produto", nullable = false)
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	@NotNull
	@Column(name = "quantidade", nullable = false)
	public int getQuantidade() {
		return quantidade;
	}

	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_venda", nullable = false)
	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	@NotNull
	@Size(max = 10)
	@Column(name = "pagamento", nullable = false, length = 10)
	public String getPagamento() {
		return pagamento;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}
	
}
