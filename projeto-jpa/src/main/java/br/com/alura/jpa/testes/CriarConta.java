package br.com.alura.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;

public class CriarConta {
	
	public static void main(String[] args) {
		
		//Instancia da fabrica de conex�es
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		//Objeto EntityManager criado para executar a persist�ncia
		EntityManager em = emf.createEntityManager();
		
		Conta conta = new Conta();
		conta.setAgencia(4311);
		conta.setConta(11310);
		conta.setTitular("Jean Santos");
		
		//Importante: TODA A��O NO BANCO TEM QUE EST� DENTRO DE UMA TRANSA��O
		
		//Inicio da Transa��o
		em.getTransaction().begin();
		
		//persist�ncia realizada
		em.persist(conta);
		
		//Realiza��o da transa��o de uma �nica vez
		em.getTransaction().commit();
		
	}
}
