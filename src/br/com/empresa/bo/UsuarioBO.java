package br.com.empresa.bo;

import br.com.empresa.dao.IUsuarioDAO;
import br.com.empresa.dao.UsuarioDAO;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.UsuarioVO;

public class UsuarioBO implements IUsuarioBO{
	
	private IUsuarioDAO usuarioDAO;
	
	public UsuarioBO() {
		usuarioDAO = new UsuarioDAO();
	}

	@Override
	public UsuarioVO validarAcesso(String login, String senha) 
			throws BOValidationException, BOException {
		
		if(login == null || login.trim().length() == 0) {
			throw new BOValidationException("Login: erro de validação. Imformação Obrigatoria.");
		}else if (senha == null || senha.trim().length() == 0) {
			throw new BOValidationException("Senha: erro de validação. Imformação Obrigatoria.");
		}
		
		UsuarioVO usuarioVO = usuarioDAO.buscarUsuario(login, senha);
		
		if(usuarioVO == null) {
			throw new BOValidationException("Usuario e/ou senha inválido!");
		}
		
		return usuarioVO;
	}

}
