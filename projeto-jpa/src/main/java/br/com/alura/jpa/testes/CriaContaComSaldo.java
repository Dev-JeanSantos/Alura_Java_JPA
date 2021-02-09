package br.com.alura.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;

public class CriaContaComSaldo {
public static void main(String[] args) {
		
		//Instancia da fabrica de conexões
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		//Objeto EntityManager criado para executar a persistencoa
		EntityManager em = emf.createEntityManager();
		
		Conta conta = new Conta();
		conta.setAgencia(4311);
		conta.setConta(12320);
		conta.setTitular("Camilla Santos");
		
		//Aqui implementado um saldo bancario depois de ser tambem implementado na entidade
		conta.setSaldo(30000.00);
		
		//Importante: TODA AÇÃO NO BANCO TEM QUE ESTÁ DENTRO DE UMA TRANSAÇÃO
		
		//Inicio da Transação
		em.getTransaction().begin();
		
		//persistencia realizada
		em.persist(conta);
		
		//Realização da transação de uma única vez
		em.getTransaction().commit();
		
	}
}
