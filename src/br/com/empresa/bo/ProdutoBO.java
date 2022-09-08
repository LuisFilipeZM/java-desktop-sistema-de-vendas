package br.com.empresa.bo;

import java.math.BigInteger;
import java.util.List;

import br.com.empresa.dao.ProdutoDAO;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.validator.CNPJValidator;
import br.com.empresa.validator.CPFValidator;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.ProdutoVO;

public class ProdutoBO implements IProdutoBO{
	
	private ProdutoDAO produtoDAO;
	
	public ProdutoBO() {
		
		produtoDAO = new ProdutoDAO();
		
	}

	@Override
	public ProdutoVO buscarProdutoPorId(ProdutoVO produtoVO) throws BOException {
		
		if(produtoVO == null || produtoVO.getId() == null) {
			throw new BOException("Ocorreu um erro ao buscar o produto pelo ID.");
		}
		
		return produtoDAO.buscarProdutoPorId(produtoVO);
		
	}

	@Override
	public List<ProdutoVO> listarProduto(BigInteger id, String descri, String status, String codbar, ClienteVO client)
			throws BOException {
				
		return produtoDAO.listarProduto(id, descri, status, codbar, client);
	}

	@Override
	public int listarProdutoCount(BigInteger id, String descri, String status, String codbar, ClienteVO client)
			throws BOException {
		// TODO Auto-generated method stub
		return produtoDAO.listarProdutoCount(id, descri, status, codbar, client);
	}

	@Override
	public void salvarProduto(ProdutoVO produtoVO) throws BOValidationException, BOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluirProduto(ProdutoVO produtoVO) throws BOValidationException, BOException {
		// TODO Auto-generated method stub
		
	}

}
