package br.com.alura.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;

public class EstadoDetached {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();

		Conta conta = new Conta();
		conta.setAgencia(4311);
		conta.setConta(12338);
		conta.setTitular("Santos");
		conta.setSaldo(2900.00);

		em.getTransaction().begin();
		em.persist(conta);
		em.getTransaction().commit();

		// Importante entender que apos fecha a conexao finaliza o estado MANAGED e
		// logo o JPA destroi a referência que ele tinha do objeto.

		// Fechamento da conexão! AQUI O ESTADO PASSA A SER O DETACHED!!!
		em.close();

		// Essa ação não irá ocorrer pois o estado manager foi finalizado
		conta.setSaldo(2500.00);

		// Para essa alteração acontecer será necessário mudar o estado para MANAGED
		// realizado no caso abaixo
		EntityManager em2 = emf.createEntityManager();

		conta.setSaldo(2500.00);

		em2.getTransaction().begin();

		// o MERGE pegará a conta DETACHED e converte ela em MANAGED novamente
		em2.merge(conta);

		em2.getTransaction().commit();
	}
}
