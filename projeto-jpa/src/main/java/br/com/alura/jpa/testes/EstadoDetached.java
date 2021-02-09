package br.com.alura.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;

public class EstadoDetached {
	public static void main(String[] args) {
		//Instancia da fabrica de conexões
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
				//Objeto EntityManager criado para executar a persistencoa
				EntityManager em = emf.createEntityManager();
				
				Conta conta = new Conta();
				conta.setAgencia(4311);
				conta.setConta(12338);
				conta.setTitular("Santos");
				
				//Aqui implementado um saldo bancario depois de ser tambem implementado na entidade
				conta.setSaldo(2900.00);
				
				//Importante: TODA AÇÃO NO BANCO TEM QUE ESTÁ DENTRO DE UMA TRANSAÇÃO
				
				//Inicio da Transação
				em.getTransaction().begin();
				
				//persistencia realizada
				em.persist(conta);
				
				//Realização da transação de uma única vez
				em.getTransaction().commit();
				
				//Importante entender que apos fecha a conexao finaliza o estado manager e 
				//logo o jpa destroi a referência que ele tinha do objeto.
				
				
				//Fechamento da conexão! AQUI O ESTADO PASSA A SER O DETACHED!!!
				em.close();			
				
				//Essa ação não irá ocorrer pois o estado manager foi finalizado
				conta.setSaldo(2500.00);
				
				
				//Para essa alteração acontecer será necessário mudar o estado para MANAGER
				//realizado no caso abaixo
				EntityManager em2 = emf.createEntityManager();
				
				conta.setSaldo(2500.00);
	
				em2.getTransaction().begin();
				
				//o merge pegará a conta detached e converte ela em manager novamente
				em2.merge(conta);
				
				em2.getTransaction().commit();
				
			}
}
