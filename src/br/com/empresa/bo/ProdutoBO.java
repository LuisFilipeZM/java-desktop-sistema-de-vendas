package br.com.empresa.bo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import br.com.empresa.dao.ProdutoDAO;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.validator.CNPJValidator;
import br.com.empresa.validator.CPFValidator;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.ProdutoVO;
import br.com.empresa.vo.enums.StatusEnum;

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
		
		if(client == null || client.getId() == null) {
			throw new BOException(); 
		}
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

		if(produtoVO == null) {
			throw new BOException("Não é possível salvar a pessoa pois o objeto é nulo.");
		}else if(produtoVO.getStatus() == null || produtoVO.getCodbar().trim().length() == 0) {
			throw new BOValidationException("Status: erro de validação. "
					+ "O Status do produto deve ser selecionado.");
		}else if(produtoVO.getDescri() == null || produtoVO.getDescri().trim().length() == 0) {
			throw new BOValidationException("Descrição: erro de validação. "
					+ "A descrição do produto deve ser preenchida.");
		}else if(produtoVO.getCodbar() == null || produtoVO.getCodbar().trim().length() == 0) {
			throw new BOValidationException("Cód. Barras: erro de validação. "
					+ "O Cód. Barras do produto deve ser preenchida.");
		}else if(produtoVO.getQtdest() == null || produtoVO.getQtdest() == BigDecimal.ZERO) {
			throw new BOValidationException("Qtd. Estoque: erro de validação. "
					+ "A Qtd. Estoque do produto deve ser preenchida.");
		}else if(produtoVO.getValcom() == null || produtoVO.getValcom() == BigDecimal.ZERO) {
			throw new BOValidationException("Vlr. Compra: erro de validação. "
					+ "O Vlr. Commpra do produto deve ser preenchida.");
		}else if(produtoVO.getValven() == null || produtoVO.getValven() == BigDecimal.ZERO) {
			throw new BOValidationException("Vlr. Venda: erro de validação. "
					+ "A Vlr. Venda do produto deve ser preenchida.");
		}
		
		produtoDAO.salvarProduto(produtoVO);
		
	}

	@Override
	public void excluirProduto(ProdutoVO produtoVO) throws BOValidationException, BOException {

		if(produtoVO == null || produtoVO.getId() == null) {
			throw new BOException("Ocorreu um erro ao excluir a pessoa.");
		}
		
		produtoDAO.excluirProduto(produtoVO);
		
	}

}
