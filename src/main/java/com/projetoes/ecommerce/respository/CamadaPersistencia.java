package com.projetoes.ecommerce.respository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.projetoes.ecommerce.model.Carro;

public class CamadaPersistencia {
	
	public static void main(String[] args) {		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoesPU");
		
		EntityManager em = emf.createEntityManager();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		em.getTransaction().begin();
		
		//Declarando os repositórios
		Carros carros = new Carros(em);
		
		//Buscando as informações do banco
		List<Carro> listaDeCarros = carros.pesquisar("");
		System.out.println(listaDeCarros);
		
		//Criando um carro
		Carro carro = new Carro();
		carro.setMarca("Toyota");
		carro.setModelo("Camry");
		try {
			carro.setAnoFabricacao(sdf.parse("2022-01-01"));
			carro.setAnoModelo(sdf.parse("2022-01-01"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		carro.setValor(245990.00);
		carro.setDescricao("Comfortable sedan");
		
		
		//Salvando o carro
		Carro retornoCarro = carros.guardar(carro);
		
		em.getTransaction().commit();
		
		//Verificando se a inserção funcionou
		List<Carro> listaDeCarros2 = carros.pesquisar("");
		System.out.println(listaDeCarros2);
		
		//Trazer carro adicionado
		System.out.println(carros.porId(retornoCarro.getId()));
		System.out.println(carros.listarTodos());
		em.close();
		emf.close();
	}

}
