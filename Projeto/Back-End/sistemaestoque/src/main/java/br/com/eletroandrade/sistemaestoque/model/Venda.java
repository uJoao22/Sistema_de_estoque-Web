package br.com.eletroandrade.sistemaestoque.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.eletroandrade.sistemaestoque.action.VendaProduto;

@Entity
@Table(name = "venda")
@JsonIgnoreProperties(ignoreUnknown = true, value = { "hibernateLazyInitializer", "handler" })
public class Venda implements Serializable {
	private static final long serialVersionUID = 3176470146682589700L;
	
	private Long id;
	private Date dataVenda;
	@JsonManagedReference
	private List<VendaProduto> produtos;
	private double valorTotal;
	private String pagamento;
	
	public Venda() {
	}

	public Venda(Long id) {
		this.id = id;
	}

	@Id
	@Column(name = "id_venda")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "venda", orphanRemoval = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	public List<VendaProduto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<VendaProduto> produtos) {
		this.produtos = produtos;
	}
	
	public boolean addProduto(VendaProduto vp) {
		if (produtos == null)
			produtos = new ArrayList<VendaProduto>(1);

		vp.setVenda(this);
		produtos.add(vp);
		return true;
	}


	@NotNull
	@Min(value=0)
	@Column(name="valor_total", columnDefinition="numeric(10,2) default 0.00")
	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
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
