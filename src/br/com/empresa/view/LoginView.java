package br.com.empresa.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.empresa.dao.Dados;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.service.ServicoBeanLocal;
import br.com.empresa.vo.UsuarioVO;

public class LoginView extends JFrame {
	
	private JTextField tfLogin;
	private JPasswordField pfSenha;

	private ServicoBeanLocal servicoBeanLocal;
	
	/**
	 * Create the frame.
	 */
	public LoginView() {
		
		servicoBeanLocal = new ServicoBeanLocal();
		
		setTitle("Autenticação do sistema");
		setBounds(100, 100, 405, 253);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				autenticarAcesso();
			}
		});
		btnEntrar.setBounds(58, 149, 112, 40);
		getContentPane().add(btnEntrar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sair();
			}
		});
		btnSair.setBounds(232, 149, 112, 40);
		getContentPane().add(btnSair);
		
		tfLogin = new JTextField();
		tfLogin.setBounds(58, 33, 286, 26);
		getContentPane().add(tfLogin);
		tfLogin.setColumns(10);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(10, 36, 94, 19);
		getContentPane().add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 104, 45, 13);
		getContentPane().add(lblSenha);
		
		pfSenha = new JPasswordField();
		pfSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				autenticarAcesso();
			}
		});
		pfSenha.setBounds(58, 98, 286, 26);
		getContentPane().add(pfSenha);
		
		//coloca janela no centro da tela
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2,
				dim.height / 2 - this.getSize().height / 2);

	}
	private void autenticarAcesso() {
		
		String login = this.tfLogin.getText();
		String senha = new String(this.pfSenha.getPassword());
		
		try {
			
			UsuarioVO usuario = servicoBeanLocal.validarUsuario(login, senha);
			
			Dados.setUsuarioLogado(usuario);
			
			SelecaoClienteView selecao = new SelecaoClienteView();
			selecao.setVisible(true);
			
			super.setVisible(false);
			super.dispose();
			
		} catch (BOValidationException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Mensagem de alerta!", JOptionPane.WARNING_MESSAGE);
		} catch (BOException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro no sistema, procure "
					+ "o administrador", "Mensagem de erro!", JOptionPane.ERROR_MESSAGE);		
		}
		
	}
	private void sair() {
		
		System.exit(0);
		
	}
}
