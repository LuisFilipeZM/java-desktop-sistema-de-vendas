package br.com.empresa.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ManutencaoProdutoView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfCodigo;
	/**
	 * Create the dialog.
	 */
	public ManutencaoProdutoView() {
		setTitle("Manutenção de Produto <2>");
		setBounds(100, 100, 408, 446);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCodigo = new JLabel("Código");
			lblCodigo.setBounds(10, 10, 87, 13);
			contentPanel.add(lblCodigo);
		}
		{
			tfCodigo = new JTextField();
			tfCodigo.setBounds(107, 7, 110, 19);
			contentPanel.add(tfCodigo);
			tfCodigo.setColumns(10);
		}
	}

}
