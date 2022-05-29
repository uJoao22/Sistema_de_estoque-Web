package br.com.eletroandrade.sistemaestoque.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tipo_usuario")
public class TipoUsuario {

	private Long id_tipo;
	private String nivel;
	
	public TipoUsuario() {
	}
	
	public TipoUsuario(Long id_tipo) {
		this.id_tipo = id_tipo;
	}

	@Id
	@Column(name = "id_tipo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId_tipo() {
		return id_tipo;
	}

	public void setId_tipo(Long id_tipo) {
		this.id_tipo = id_tipo;
	}

	@NotNull
	@Size(max = 30)
	@Column(name = "nivel", nullable = false, length = 30)
	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
}
