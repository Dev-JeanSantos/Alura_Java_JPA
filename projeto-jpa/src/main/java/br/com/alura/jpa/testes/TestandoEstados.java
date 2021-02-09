package br.com.alura.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;

public class TestandoEstados {
	public static void main(String[] args) {
		
		//ESTADO TRANSIENT DO JPA
		//(A classe nesse momento não tem relação nenhuma com o JPA e é um possivel candidato a ser MANAGED)
		//IMPORTANTE (Não possui ID)
		
		Conta conta = new Conta();
		conta.setAgencia(2000);
		conta.setConta(11322);
		conta.setTitular("Marcos Almeida");
		conta.setSaldo(20000.00);
		
		//****************************************//
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();
		
		//****************************************//
		em.getTransaction().begin();
		//****************************************//
		
		//CONVERTENDO O ESTADO TRANSIENT EM MANAGED
		//AQUI OCORRE A SINCRONIZAÇÃO AUTOMÁTICA COM O BANCO
		em.persist(conta);
		
		//****************************************//
		
		//AQUI ESSE OBJETO FOI REMOVIDO DO CONTEXTO JPA E TAMBÉM DO BANCO (DELETE)
		//CONVERTENDO O ESTADO MANAGED EM REMOVED
		em.remove(conta);
		
		//****************************************//
		
		em.getTransaction().commit();
		
	}
}
