package br.com.empresa.bo;

import java.util.List;

import br.com.empresa.dao.IUsuarioClienteDAO;
import br.com.empresa.dao.UsuarioClienteDAO;
import br.com.empresa.exception.BOException;
import br.com.empresa.vo.UsuarioClienteVO;
import br.com.empresa.vo.UsuarioVO;

public class UsuarioClienteBO implements IUsuarioClienteBO {
	
	private IUsuarioClienteDAO usuarioClienteDAO;

	public UsuarioClienteBO() {
		super();
		usuarioClienteDAO = new UsuarioClienteDAO();
	}

	@Override
	public List<UsuarioClienteVO> listarClientesUsuario(UsuarioVO usuario) throws BOException {
		
		if(usuario == null || usuario.getId() == null) {
			throw new BOException("Ocorreu um erro ao listar clientes de um usuário.");
		}
		
		return usuarioClienteDAO.listarClientesUsuario(usuario);
	}

	@Override
	public int buscarQuantidadeClientesUsuario(UsuarioVO usuario) throws BOException {
		
		if(usuario == null || usuario.getId() == null) {
			throw new BOException("Ocorreu um erro ao buscar quantidade de"
					+ " clientes de um usuário.");
		}
		
		return usuarioClienteDAO.buscarQuantidadeClientesUsuario(usuario);
	}

}
