package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.alura.jpa.modelo.Conta;
import br.com.alura.jpa.modelo.Movimentacao;

public class TesteJPQL {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();
		
		//*******************************************************//
		//Criado um objeto Conta e instanciado o id que queremos buscar no bd
		Conta conta = new Conta();
		conta.setId(2L);
		
		//*******************************************************//
		/*
		 * A JPQL e sua sintaxe! Lembrando: => Serão utilizados os objetos e não as tabelas do BD; 
		 * => O uso de Álias (Apelidos) para os objetos é obrigatório
		 * =>A marcação :p é importante para apontar as chamadas no setParameter()
		 */		
		String jpql = "select m from Movimentacao m where m.conta = :pConta";
		
		//*******************************************************//
		//O CREATEQUERY é o Objeto da JPA responsavel em processa o JPQL		
		Query query = em.createQuery(jpql);
		
		//*******************************************************//
		//O SETPARAMETER é o metodo da QUERY que recebe o nome do parametro e objeto dele.	
		query.setParameter("pConta", conta);
		
		//*******************************************************//
		//O RESULTLIST é o metodo da query que gera uma lista dos resultados do processo dessa consulta JPQL	
		List <Movimentacao> resultList = query.getResultList();
		
		for (Movimentacao movimentacao : resultList) {
			System.out.println("Descrição: " + movimentacao.getDescricao());
			System.out.println("Tipo: " + movimentacao.getMovimentacao());
		}
	}
}
