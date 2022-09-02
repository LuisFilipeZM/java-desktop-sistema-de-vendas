package br.com.empresa.bo;

import java.util.List;
import java.util.Map;

import br.com.empresa.dao.PessoaDAO;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.validator.CNPJValidator;
import br.com.empresa.validator.CPFValidator;
import br.com.empresa.validator.ValidatorFormatException;
import br.com.empresa.validator.ValidatorInvalidValueException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.PessoaVO;

public class PessoaBO implements IPessoaBO {
	
	private PessoaDAO pessoaDAO;
	
	public PessoaBO() {
		pessoaDAO = new PessoaDAO();
	}

	@Override
	public List<PessoaVO> listarPessoas(String tipoPessoa, String nomePessoa, String cpfCnpj, String cidade,
			String estado, ClienteVO cliente) throws BOValidationException, BOException {

		if(cliente == null || cliente.getId() == null) {
			throw new BOException();
		}
		
		if(cpfCnpj != null && cpfCnpj.trim().length() > 1) {
			
			if(tipoPessoa.equals("F")) {

				CPFValidator cpfValidator = new CPFValidator();
				try {
					cpfValidator.validate(cpfCnpj);
				} catch (Exception e) {
					throw new BOValidationException("CPF: erro de validação. "
							+ "O CPF informado está incorreto.");
				}
				
			}else if(tipoPessoa.equals("J") && 
					cpfCnpj.replaceAll("\\,", "").replaceAll("/,", "").replaceAll("-,", "")
					.length() > 0) {
				CNPJValidator cnpjValidator = new CNPJValidator();
				try {
					cnpjValidator.validate(cnpjValidator);
				} catch (Exception e) {
					throw new BOValidationException("CNPJ: erro de validação. "
							+ "O CNPJ informado está incorreto");
				}
			}
		}
		
		return pessoaDAO.listarPessoas(tipoPessoa, nomePessoa, cpfCnpj, 
				cidade, estado, cliente);
	}

	@Override
	public void salvarPessoa(PessoaVO pessoa) throws BOValidationException, BOException {
		
		
		
	}

	@Override
	public void excluirPessoa(PessoaVO pessoa) throws BOValidationException, BOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PessoaVO buscarPessoaPorId(PessoaVO pessoa) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaVO> ListarPessoas(int first, int pageSize, Map<String, Object> filters, ClienteVO cliente)
			throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
