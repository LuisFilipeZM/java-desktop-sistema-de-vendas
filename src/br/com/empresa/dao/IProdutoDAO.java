package br.com.empresa.dao;

import java.math.BigInteger;
import java.util.List;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.ProdutoVO;

public interface IProdutoDAO {

	public abstract ProdutoVO buscarProdutoPorId(ProdutoVO produtoVO) 
			throws BOException;
	
	public abstract List<ProdutoVO> listarProduto(BigInteger id, String descri, String status, String codbar, ClienteVO client) 
			throws BOException;
	
	public abstract int listarProdutoCount(BigInteger id, String descri, String status, String codbar, ClienteVO client) 
			throws BOException;
	
	public abstract void salvarProduto(ProdutoVO produtoVO) 
			throws BOValidationException, BOException;
	
	public abstract void excluirProduto(ProdutoVO produtoVO) 
			throws BOValidationException, BOException;
	
}
