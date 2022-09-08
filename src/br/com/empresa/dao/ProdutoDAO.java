package br.com.empresa.dao;

import java.math.BigInteger;
import java.util.List;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.ProdutoVO;

public class ProdutoDAO implements IProdutoDAO{

	@Override
	public ProdutoVO buscarProdutoPorId(ProdutoVO produtoVO) throws BOException {
		
		for(int i = 0; i < Dados.getProdutoVOs().size(); i++) {
			if(Dados.getProdutoVOs().get(i).equals(produtoVO)) {
				return Dados.getProdutoVOs().get(i);
			}
		}

		return null;
	}

	@Override
	public List<ProdutoVO> listarProduto(BigInteger id, String descri, String status, String codbar, ClienteVO client)
			throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int listarProdutoCount(BigInteger id, String descri, String status, String codbar, ClienteVO client)
			throws BOException {
		// TODO Auto-generated method stub
		return 0;
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
