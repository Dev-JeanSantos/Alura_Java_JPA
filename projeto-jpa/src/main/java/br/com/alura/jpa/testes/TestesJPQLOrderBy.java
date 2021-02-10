package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.alura.jpa.modelo.Conta;
import br.com.alura.jpa.modelo.Movimentacao;

public class TestesJPQLOrderBy {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();
		
		//*******************************************************//
		//Criado um objeto Conta e instanciado o id que queremos buscar no bd
		Conta conta = new Conta();
		conta.setId(2L);
		
		//*******************************************************//
		//Aqui no final implementamos o ORDER BY usando alias e setando o atributo VALOR definindo o DESC ou ASC
		String jpql = "select m from Movimentacao m where m.conta = :pConta order by m.valor desc";
		
		//*******************************************************//
		//O TYPEDQUERY é o Objeto da JPA mais especifico onde definimos o tipo de lista que obteremos em nossa consulta
		//assim poderemos evitar erros e exceções.
		TypedQuery<Movimentacao> query = em.createQuery(jpql, Movimentacao.class);
		
		//*******************************************************//
		//O SETPARAMETER é o metodo da QUERY que recebe o nome do parametro e objeto dele.	
		query.setParameter("pConta", conta);
		
		//*******************************************************//
		//O RESULTLIST é o metodo da query que gera uma lista dos resultados do processo dessa consulta JPQL	
		List <Movimentacao> resultList = query.getResultList();
		
		for (Movimentacao movimentacao : resultList) {
			System.out.println("Descrição: " + movimentacao.getDescricao());
			System.out.println("Valor: " + movimentacao.getValor());
			System.out.println("Tipo: " + movimentacao.getMovimentacao());
		}
	}
}
