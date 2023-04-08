package com.ufcg.psoft.mercadofacil.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ufcg.psoft.mercadofacil.model.Produto;
 
@Repository
public class ProdutoVolatilStubRepository implements ProdutoRepository<Produto, Long> {

	@Override
	public Produto save(Produto produto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produto find(Long id) {
		if(id == 10L) {
			return Produto.builder()
	                .id(10L)
	                .nome("Produto Dez")
	                .codigoBarra("4012345678901")
	                .fabricante("Fabricante Dez")
	                .preco(450.0)
	                .build();
		}
		return null;
	}

	@Override
	public List<Produto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produto update(Produto produto) {
		if(produto.getId().longValue() == 10L) {
			return Produto.builder()
	                .id(10L)
	                .nome("Produto Dez Alterado")
	                .codigoBarra("7896649000050")
	                .fabricante("Belo Monte")
	                .preco(20.0)
	                .build(); 
		}
		return null;
	}

	@Override
	public void delete(Produto produto) {
		// TODO Auto-generated method stub
		
	}



}
