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
		 * A JPQL e sua sintaxe! Lembrando: => Ser�o utilizados os objetos e n�o as tabelas do BD; 
		 * => O uso de �lias (Apelidos) para os objetos � obrigat�rio
		 * =>A marca��o :p � importante para apontar as chamadas no setParameter()
		 */		
		String jpql = "select m from Movimentacao m where m.conta = :pConta";
		
		//*******************************************************//
		//O CREATEQUERY � o Objeto da JPA responsavel em processa o JPQL		
		Query query = em.createQuery(jpql);
		
		//*******************************************************//
		//O SETPARAMETER � o metodo da QUERY que recebe o nome do parametro e objeto dele.	
		query.setParameter("pConta", conta);
		
		//*******************************************************//
		//O RESULTLIST � o metodo da query que gera uma lista dos resultados do processo dessa consulta JPQL	
		List <Movimentacao> resultList = query.getResultList();
		
		for (Movimentacao movimentacao : resultList) {
			System.out.println("Descri��o: " + movimentacao.getDescricao());
			System.out.println("Tipo: " + movimentacao.getMovimentacao());
		}
	}
}
