package br.com.alura.jpa.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.alura.jpa.enums.TipoMovimentacao;


//***********************************************//
// NÃO ESQUECER DE DECLARAR A CLASSE NO PERSISTENCE.XML
//***********************************************//


//Declaração de Entidade pro JPA
@Entity
public class Movimentacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private BigDecimal valor;
	
	//Annotation para mapeamento no JPA de ENUM
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao movimentacao;
	
	private LocalDateTime data;
	private String descricao;
	
	//MAPEAMENTO MUITOS PARA UM//
	//MUITAS MOVIMENTAÇÕES PARA UMA CONTA//
	@ManyToOne
	private Conta conta;
	
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public TipoMovimentacao getMovimentacao() {
		return movimentacao;
	}
	public void setMovimentacao(TipoMovimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}	
}
