package com.ufcg.psoft.mercadofacil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;

@Service
public class ProdutoAlterarPadraoService implements ProdutoAlterarService{
	
	@Autowired
	ProdutoRepository<Produto, Long> produtoRepository;
	
	ValidaCodigoDeBarrasService validaCodigoDeBarras = new ValidaCodigoDeBarrasServiceImpl();

	@Override
	public Produto alterar(Produto produto) {
		if (!this.nomeIsValid(produto.getNome())){
			throw new IllegalArgumentException("O nome não pode ser vazio.");
		}  
		 
		if(!this.precoIsValid(produto.getPreco())){
			throw new IllegalArgumentException("O preço não pode ser igual ou menor que zero.");
		} 
		
		if(!this.validaCodigoDeBarras.isValid(produto.getCodigoBarra())){
			throw new IllegalArgumentException("O código de Barras informado não é válido.");
		} 
		
		if(!this.fabricanteIsValid(produto.getFabricante())) {
			throw new IllegalArgumentException("O fabricante não pode ser vazio.");
		} 
		
		return produtoRepository.update(produto);
		
	}
	
	
	private boolean nomeIsValid(String nome) {
		return nome != "";
	}
	
	private boolean precoIsValid(double preco) {
		return preco > 0;
	}
	
	private boolean fabricanteIsValid(String fabricante) {
		return fabricante != "";
	}
	
}
