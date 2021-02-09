package br.com.alura.jpa.testes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.enums.TipoMovimentacao;
import br.com.alura.jpa.modelo.Categoria;
import br.com.alura.jpa.modelo.Conta;
import br.com.alura.jpa.modelo.Movimentacao;

public class TestaRelacionamentoMovimentaçãoCategoria {
	
	public static void main(String[] args) {
		
		Categoria cat1 = new Categoria("Viagem");
		Categoria cat2 = new Categoria("Negócios");
		
		Conta conta = new Conta();
		conta.setId(3L);
			
		Movimentacao mov1 = new Movimentacao();
		mov1.setDescricao("Viagem a SP");
		mov1.setMovimentacao(TipoMovimentacao.SAÍDA);
		mov1.setData(LocalDateTime.now());
		mov1.setValor(new BigDecimal(250.00));
		mov1.setCategorias(Arrays.asList(cat1, cat2));
		mov1.setConta(conta);
		
		Movimentacao mov2 = new Movimentacao();
		mov2.setDescricao("Viagem a RJ");
		mov2.setMovimentacao(TipoMovimentacao.SAÍDA);
		mov2.setData(LocalDateTime.now());
		mov2.setValor(new BigDecimal(400.00));
		mov2.setCategorias(Arrays.asList(cat1, cat2));	
		mov2.setConta(conta);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(cat1);
		em.persist(cat2);
		em.persist(mov1);
		em.persist(mov2);
		em.getTransaction().commit();
		em.close();
	}
}
