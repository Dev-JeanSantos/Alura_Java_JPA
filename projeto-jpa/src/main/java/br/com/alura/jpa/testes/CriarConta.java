package br.com.alura.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;

public class CriarConta {
	
	public static void main(String[] args) {
		
		//Instancia da fabrica de conexões
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		//Objeto EntityManager criado para executar a persistencoa
		EntityManager em = emf.createEntityManager();
		
		Conta conta = new Conta();
		conta.setAgencia(4311);
		conta.setConta(11310);
		conta.setTitular("Jean Santos");
		
		//Importante: TODA AÇÃO NO BANCOTEM QUE ESTÁ DENTRO DE UMA TRANSAÇÃO
		
		//Inicio da Tranação
		em.getTransaction().begin();
		
		//persistencia realizada
		em.persist(conta);
		
		//Realização da transação de uma única vez
		em.getTransaction().commit();
		
	}
}
