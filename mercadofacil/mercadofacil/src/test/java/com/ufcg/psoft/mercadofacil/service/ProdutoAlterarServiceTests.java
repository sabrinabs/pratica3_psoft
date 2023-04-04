package com.ufcg.psoft.mercadofacil.service;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import com.ufcg.psoft.mercadofacil.service.ProdutoAlterarService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

@SpringBootTest
@DisplayName("Testes do Serviço de alteração do produto")
public class ProdutoAlterarServiceTests {
	
	@Autowired
	ProdutoAlterarService driver;
	
	@MockBean
	ProdutoRepository<Produto, Long>  produtoRepository;
	
	Produto produto;
	
	@BeforeEach
	void setup() {
		Mockito.when(produtoRepository.find(10L))
				.thenReturn(Produto.builder()
						.id(10L)
						.codigoBarra("213587645734")
						.nome("Produto Dez")
						.fabricante("Empresa Dez")
						.preco(450.0)
						.build()
				);
		produto = produtoRepository.find(10L);
	}
	
	@Test
	@DisplayName("Quando um novo nome válido for fornecido para o produto")
	void quandoNovoNomeValido() {
		// Arrange
		produto.setNome("Produto Dez Atualizado");
		Mockito.when(produtoRepository.update(produto))
				.thenReturn(Produto.builder()
						.id(10L)
						.codigoBarra("213587645734")
						.nome("Produto Dez Atualizado")
						.fabricante("Empresa Dez")
						.preco(450.0)
						.build()
				);
		
		// Act
		Produto resultado = driver.alterar(produto);
		
		// Assert
		assertEquals("Produto Dez Atualizado", resultado.getNome());
	}
	
}
