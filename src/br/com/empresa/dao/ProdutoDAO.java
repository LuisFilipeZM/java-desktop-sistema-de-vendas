package br.com.empresa.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.PessoaVO;
import br.com.empresa.vo.ProdutoVO;

public class ProdutoDAO implements IProdutoDAO {

	@Override
	public ProdutoVO buscarProdutoPorId(ProdutoVO produtoVO) throws BOException {

		for (int i = 0; i < Dados.getProdutoVOs().size(); i++) {
			if (Dados.getProdutoVOs().get(i).equals(produtoVO)) {
				return Dados.getProdutoVOs().get(i);
			}
		}

		return null;
	}

	@Override
	public List<ProdutoVO> listarProduto(BigInteger id, String descri, String status, String codbar, ClienteVO client)
			throws BOException {

		List<ProdutoVO> produto = Dados.getProdutoVOs();
		List<ProdutoVO> retorno = new ArrayList<ProdutoVO>();

		for (ProdutoVO produtoVO : produto) {

			if (produtoVO.getClient().equals(client) == false) {
				continue;
			}

			if (id != null) {
				if (produtoVO.getId() != null && produtoVO.getId().equals(id) == false) {
					continue;
				}
			}

			if (descri != null && descri.trim().length() > 0) {
				if (produtoVO.getDescri().contains(descri) == false) {
					continue;
				}
			}

			if (status != null && status.trim().length() > 0) {
				if (produtoVO.getStatus() != null && produtoVO.getStatus().contains(status) == false) {
					continue;
				}
			}

			if (codbar != null) {
				if (produtoVO.getCodbar() != null && produtoVO.getCodbar().equals(codbar) == false) {
					continue;
				}
			}

			retorno.add(produtoVO);

		}

		return retorno;
	}

	@Override
	public int listarProdutoCount(BigInteger id, String descri, String status, String codbar, ClienteVO client)
			throws BOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void salvarProduto(ProdutoVO produtoVO) throws BOValidationException, BOException {
		
		List<ProdutoVO> produtoVOs = Dados.getProdutoVOs();
		
		if(produtoVO.getId() == null) {
			
			if(produtoVOs.size() > 0) {
				ProdutoVO ultimoProdutoVO = produtoVOs.get(produtoVOs.size() - 1);
				produtoVO.setId(ultimoProdutoVO.getId().add(BigInteger.ONE));
			}else {
				produtoVO.setId(BigInteger.ONE);
			}
			
			Dados.getProdutoVOs().add(produtoVO);
			
		}else {
			
			for(int i = 0; i < produtoVOs.size(); i++) {
				if(produtoVOs.get(i).equals(produtoVO)) {
					Dados.getProdutoVOs().set(i, produtoVO);
				}
			}
			
		}

	}

	@Override
	public void excluirProduto(ProdutoVO produtoVO) throws BOValidationException, BOException {

		for(int i = 0; i < Dados.getProdutoVOs().size(); i++) {
			if(Dados.getProdutoVOs().get(i).equals(produtoVO)) {
				Dados.getProdutoVOs().remove(i);
			}
		}

	}

}
