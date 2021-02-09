package br.com.alura.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Cliente;
import br.com.alura.jpa.modelo.Conta;

public class TesteRelacionamentoClienteConta {
	
	public static void main(String[] args) {
		
		Conta conta = new Conta();
		conta.setId(2L);
		
		Cliente cliente = new Cliente();
		cliente.setNome("Santos");
		cliente.setEndereco("Rua Manoel Novis, 201 Centro Tangua-RJ");
		cliente.setProfissao("Desenvolvedor de Sistemas");
		cliente.setConta(conta);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
		em.close();
		
	}
}
