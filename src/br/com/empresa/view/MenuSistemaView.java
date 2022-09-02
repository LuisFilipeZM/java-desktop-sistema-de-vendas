package br.com.empresa.view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuSistemaView extends JFrame {


	public MenuSistemaView() {
		setTitle("Sistema simples de venda");
		setBounds(100, 100, 760, 517);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mniSair = new JMenuItem("Sair");
		mniSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sair();
			}
		});
		mnArquivo.add(mniSair);
		
		JMenu mnManutencao = new JMenu("Manutenção");
		menuBar.add(mnManutencao);
		
		JMenuItem mniConsumidor = new JMenuItem("Consumidor/Fornecedor");
		mniConsumidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manterConsumidorFornecedor();
			}
		});
		mnManutencao.add(mniConsumidor);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		JMenuItem mniSobre = new JMenuItem("Sobre");
		mniSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sobre();
			}
		});
		mnAjuda.add(mniSobre);

		setExtendedState(Frame.MAXIMIZED_BOTH);
		
		try {
			
			InputStream streamLogo = getClass().getResourceAsStream("senac_logo.png");
			BufferedImage img = ImageIO.read(streamLogo);
			
			ImageIcon imageIcon = new ImageIcon(img);
			JLabel centerLabel = new JLabel(imageIcon);
			
			JPanel main = new JPanel(new BorderLayout());
			main.add(centerLabel, BorderLayout.CENTER);
			
			getContentPane().add(main, BorderLayout.CENTER);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao abrir tela.",
					"Erro!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void manterConsumidorFornecedor() {
		
		JDialog consultaConsumidorFornecedorView = new ConsultaConsumidorForencedorView();
		consultaConsumidorFornecedorView.setModal(true);
		consultaConsumidorFornecedorView.setVisible(true);
		
	}
	
	private void sair() {
		Object[] options = {"Sim", "Não"};
		
		int n = JOptionPane.showOptionDialog(
				null,
				"Tem certeza?",
				"Comfirmação",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[1]);
		
		if(n == 0) {
			System.exit(0);
		}
				
	}
	
	private void sobre() {
		JOptionPane.showMessageDialog(null, "Top");
	}

}
