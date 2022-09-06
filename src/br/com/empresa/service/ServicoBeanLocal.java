package br.com.empresa.service;

import java.util.List;
import java.util.Map;

import br.com.empresa.bo.IPessoaBO;
import br.com.empresa.bo.IUsuarioBO;
import br.com.empresa.bo.IUsuarioClienteBO;
import br.com.empresa.bo.PessoaBO;
import br.com.empresa.bo.UsuarioBO;
import br.com.empresa.bo.UsuarioClienteBO;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.PessoaVO;
import br.com.empresa.vo.UsuarioClienteVO;
import br.com.empresa.vo.UsuarioVO;

public class ServicoBeanLocal implements IServicoBeanLocal {

	@Override
	public UsuarioVO validarAcesso(String login, String senha) 
			throws BOValidationException, BOException {
		
		IUsuarioBO usuarioBO = new UsuarioBO();
		
		return usuarioBO.validarAcesso(login, senha);
	}

	@Override
	public List<UsuarioClienteVO> listarClientesUsuario(UsuarioVO usuario) throws BOException {
		
		IUsuarioClienteBO usuarioClienteBO = new UsuarioClienteBO();
		
		return usuarioClienteBO.listarClientesUsuario(usuario);
	}

	@Override
	public int buscarQuantidadeClientesUsuario(UsuarioVO usuario) throws BOException {
		
		IUsuarioClienteBO usuarioClienteBO = new UsuarioClienteBO();
		
		return usuarioClienteBO.buscarQuantidadeClientesUsuario(usuario);
	}

	@Override
	public List<PessoaVO> listarPessoas(String tipoPessoa, String nomePessoa, String cpfCnpj, String cidade,
			String estado, ClienteVO cliente) throws BOValidationException, BOException {
		
		IPessoaBO pessoaBO = new PessoaBO();
		
		return pessoaBO.listarPessoas(tipoPessoa, nomePessoa, cpfCnpj, 
				cidade, estado, cliente);
	}

	@Override
	public void salvarPessoa(PessoaVO pessoa) throws BOValidationException, BOException {
		
		IPessoaBO pessoaBO = new PessoaBO();
		
		pessoaBO.salvarPessoa(pessoa);
		
	}

	@Override
	public void excluirPessoa(PessoaVO pessoa) throws BOValidationException, BOException {
		
		IPessoaBO pessoaBO = new PessoaBO();
		
		pessoaBO.excluirPessoa(pessoa);
		
	}

	@Override
	public PessoaVO buscarPessoaPorId(PessoaVO pessoa) throws BOException {
		
		IPessoaBO pessoaBO = new PessoaBO();
		
		return pessoaBO.buscarPessoaPorId(pessoa);
	}

	@Override
	public List<PessoaVO> listarPessoas(int first, int pageSize, Map<String, Object> filters, ClienteVO cliente)
			throws BOException {
		IPessoaBO pessoaBO = new PessoaBO();
		
		return pessoaBO.listarPessoas(first, pageSize, filters, cliente);
	}

}






