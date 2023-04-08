package com.ufcg.psoft.mercadofacil.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;

import jakarta.servlet.ServletException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes do controlador de Produtos")
public class ProdutoV1ControllerTests {

	@Autowired
	MockMvc driver;

	@Autowired
	ProdutoRepository<Produto, Long> produtoRepository;

	ObjectMapper objectMapper = new ObjectMapper();

	Produto produto;

	@BeforeEach
	void setup() {
		produto = produtoRepository.find(10L);

	}

	@Test
	@DisplayName("Quando alteramos o nome do produto com dados válidos")
	void quandoAlteramosNomeDoProdutoValido() throws Exception {
		// Arrange
		produto.setNome("Produto Dez Alterado");
		// Act

		String responseJsonString = driver
				.perform(put("/v1/produtos/" + produto.getId()).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(produto)))
				.andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();

		Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();

		// Assert
		assertEquals(resultado.getNome(), "Produto Dez Alterado");
	}

	@Test
	@DisplayName("Quando alteramos o nome do produto com dados inválidos")
	void quandoAlteramosNomeDoProdutoInvalido() throws Exception {
		produto.setNome("");

		try {
			driver.perform(put("/v1/produtos/" + produto.getId()).contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(produto))).andExpect(status().isOk()).andDo(print())
					.andReturn().getResponse().getContentAsString();

		} catch (ServletException e) {
			var meuerro = e.getCause();
			assertEquals("O nome não pode ser vazio.", meuerro.getMessage());
		}

	}

	@Test
	@DisplayName("Quando alteramos o preço do produto com dados válidos")
	void quandoAlteramosPrecoDoProdutoValido() throws Exception {
		// Arrange
		produto.setPreco(20.0);
		// Act

		String responseJsonString = driver
				.perform(put("/v1/produtos/" + produto.getId()).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(produto)))
				.andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();

		Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();

		// Assert
		assertEquals(resultado.getPreco(), 20.0);
	}

	@Test
	@DisplayName("Quando alteramos o preço do produto com dados inválidos")
	void quandoAlteramosPrecoDoProdutoInvalido() throws Exception {
		produto.setPreco(0);
		;

		try {
			driver.perform(put("/v1/produtos/" + produto.getId()).contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(produto))).andExpect(status().isOk()).andDo(print())
					.andReturn().getResponse().getContentAsString();

		} catch (ServletException e) {
			var meuerro = e.getCause();
			assertEquals("O preço não pode ser igual ou menor que zero.", meuerro.getMessage());
		}

	}

	@Test
	@DisplayName("Quando altero o produto com fabricante válido")
	void quandoAlteramosFabricanteDoProdutoValido() throws Exception {
		produto.setFabricante("Belo Monte");
		;

		// Act
		String responseJsonString = driver
				.perform(put("/v1/produtos/" + produto.getId()).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(produto)))
				.andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();

		Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();

		// Assert
		assertEquals(resultado.getFabricante(), "Belo Monte");
	}

	@Test
	@DisplayName("Quando alteramos o fabricante do produto com dados inválidos")
	void quandoAlteramosFabricanteDoProdutoInvalido() throws Exception {
		produto.setFabricante("");

		try {
			driver.perform(put("/v1/produtos/" + produto.getId()).contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(produto))).andExpect(status().isOk()).andDo(print())
					.andReturn().getResponse().getContentAsString();

		} catch (ServletException e) {
			var meuerro = e.getCause();
			assertEquals("O fabricante não pode ser vazio.", meuerro.getMessage());
		}
	}

	@Test
	@DisplayName("Quando altero o produto com código De Barras válido")
	void quandoAlteramosCodigoDeBarrasDoProdutoValido() throws Exception {
		produto.setCodigoBarra("7896649000050");

		// Act
		String responseJsonString = driver
				.perform(put("/v1/produtos/" + produto.getId()).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(produto)))
				.andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();

		Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();

		// Assert
		assertEquals(resultado.getCodigoBarra(), "7896649000050");
	}

	@Test
	@DisplayName("Quando alteramos o codido De Barras do produto com dados inválidos")
	void quandoAlteramosCodigoDeBarrasDoProdutoInvalido() throws Exception {
		produto.setCodigoBarra("4012345678902");

		try {
			driver.perform(put("/v1/produtos/" + produto.getId()).contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(produto))).andExpect(status().isOk()).andDo(print())
					.andReturn().getResponse().getContentAsString();

		} catch (ServletException e) {
			var meuerro = e.getCause();
			assertEquals("O código de Barras informado não é válido.", meuerro.getMessage());
		}
	}

	@Test
	@DisplayName("Quando alteramos o codido De Barras do produto com dados inválidos")
	void quandoAlteramosCodigoDeBarrasDoProdutoInvalido2() throws Exception {
		produto.setCodigoBarra("40");

		try {
			driver.perform(put("/v1/produtos/" + produto.getId()).contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(produto))).andExpect(status().isOk()).andDo(print())
					.andReturn().getResponse().getContentAsString();

		} catch (ServletException e) {
			var meuerro = e.getCause();
			assertEquals("O código de Barras informado não é válido.", meuerro.getMessage());
		}
	}
}
