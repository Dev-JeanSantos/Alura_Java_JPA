package br.com.alura.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;

public class AlteraConta {
	
	public static void main(String[] args) {
		//Instancia da fabrica de conex�es
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		//Objeto EntityManager criado para executar a persistencia
		EntityManager em = emf.createEntityManager();
		
		//Cria um objeto conta e com o metodo find passa a classe da altera��o e o id do dado a ser aletrado
		Conta alterarSaldo = em.find(Conta.class, 1L);
	
		
		//Importante: TODA A��O NO BANCO TEM QUE EST� DENTRO DE UMA TRANSA��O
		
		//Inicio da Transa��o
		em.getTransaction().begin();
		
		alterarSaldo.setSaldo(50000.00);
		//persistencia realizada
		em.persist(alterarSaldo);
		
		//Realiza��o da transa��o de uma �nica vez
		em.getTransaction().commit();

	}
	
}
