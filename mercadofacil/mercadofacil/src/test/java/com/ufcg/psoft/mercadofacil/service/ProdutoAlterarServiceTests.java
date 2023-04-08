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
	ProdutoRepository<Produto, Long> produtoRepository;

	Produto produto;

	@BeforeEach
	void setup() {
		Mockito.when(produtoRepository.find(10L)).thenReturn(Produto.builder().id(10L).codigoBarra("4012345678901")
				.nome("Produto Dez").fabricante("Empresa Dez").preco(450.0).build());
		produto = produtoRepository.find(10L);

		Mockito.when(produtoRepository.update(produto))
				.thenReturn(Produto.builder().id(10L).codigoBarra("7896649000050").nome("Produto Dez Atualizado")
						.fabricante("Belo Monte").preco(20.0).build());

	}

	@Test
	@DisplayName("Quando um novo nome válido for fornecido para o produto")
	void quandoNovoNomeValido() {
		// Arrange
		produto.setNome("Produto Dez Atualizado");

		Produto resultado = driver.alterar(produto);

		// Assert
		assertEquals("Produto Dez Atualizado", resultado.getNome());
	}

	@Test
	@DisplayName("Quando um novo nome Inválido for fornecido para o produto")
	void quandoNovoNomeInvalido() {
		// Arrange
		produto.setNome("");

		try {
			// Act
			driver.alterar(produto);
			fail("A exceção não foi lançada.");
		} catch (IllegalArgumentException e) {
			// Assert
			assertEquals("O nome não pode ser vazio.", e.getMessage());
		}
	}

	@Test
	@DisplayName("Quando um novo preço válido for fornecido para o produto")
	void quandoNovoPrecoValido() {
		// Arrange
		produto.setPreco(20.0);

		Produto resultado = driver.alterar(produto);

		// Assert
		assertEquals(20.0, resultado.getPreco());
	}

	@Test
	@DisplayName("Quando um novo preço inválido, for fornecido para o produto")
	void quandoNovoPrecoInvalido() {
		// Arrange
		produto.setPreco(0.0);

		try {
			// Act
			driver.alterar(produto);
			fail("A exceção não foi lançada.");
		} catch (IllegalArgumentException e) {
			// Assert
			assertEquals("O preço não pode ser igual ou menor que zero.", e.getMessage());
		}
	}

	@Test
	@DisplayName("Quando um novo codigo De Barras válido for fornecido para o produto")
	void quandoNovoCodigoDeBarrasValido() {
		// Arrange
		produto.setCodigoBarra("7896649000050");

		// Act

		Produto resultado = driver.alterar(produto);

		// Assert
		assertEquals("7896649000050", resultado.getCodigoBarra());
	}

	@Test
	@DisplayName("Quando um novo codigo De Barras inválido, for fornecido para o produto")
	void quandoNovoCodigoDeBarrasInvalido() {
		// Arrange
		produto.setCodigoBarra("4012345678902");

		try {
			// Act
			driver.alterar(produto);
			fail("A exceção não foi lançada.");
		} catch (IllegalArgumentException e) {
			// Assert
			assertEquals("O código de Barras informado não é válido.", e.getMessage());
		}
	}

	@Test
	@DisplayName("Quando um novo codigo De Barras inválido, for fornecido para o produto")
	void quandoNovoCodigoDeBarrasInvalido2() {
		// Arrange
		produto.setCodigoBarra("40");

		try {
			// Act
			driver.alterar(produto);
			fail("A exceção não foi lançada.");
		} catch (IllegalArgumentException e) {
			// Assert
			assertEquals("O código de Barras informado não é válido.", e.getMessage());
		}
	}

	@Test
	@DisplayName("Quando um novo fabricante válido for fornecido para o produto")
	void quandoNovoFabricanteValido() {
		// Arrange
		produto.setFabricante("Belo Monte");

		Produto resultado = driver.alterar(produto);

		// Assert
		assertEquals("Belo Monte", resultado.getFabricante());
	}

	@Test
	@DisplayName("Quando um novo fabricante inválido, for fornecido para o produto")
	void quandoNovoFabricanteInvalido() {
		// Arrange
		produto.setFabricante("");

		try {
			// Act
			driver.alterar(produto);
			fail("A exceção não foi lançada.");
		} catch (IllegalArgumentException e) {
			// Assert
			assertEquals("O fabricante não pode ser vazio.", e.getMessage());
		}
	}

}
