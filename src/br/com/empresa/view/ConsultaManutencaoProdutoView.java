package br.com.empresa.view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ConsultaManutencaoProdutoView extends JDialog {
	private JTextField tfCodigo;
	private JTextField tfDescricao;
	private JTextField tfCodBarras;
	private JTable table;

	/**
	 * Create the dialog.
	 */
	public ConsultaManutencaoProdutoView() {
		setTitle("Manutenção de Produto");
		setBounds(100, 100, 710, 469);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 676, 102);
		panel.setBorder(new LineBorder(Color.BLACK));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Código");
		lblCodigo.setBounds(10, 10, 44, 13);
		panel.add(lblCodigo);
		
		tfCodigo = new JTextField();
		tfCodigo.setBounds(64, 7, 96, 19);
		panel.add(tfCodigo);
		tfCodigo.setColumns(10);
		
		JLabel lblDescricao = new JLabel("Descrição");
		lblDescricao.setBounds(170, 10, 59, 13);
		panel.add(lblDescricao);
		
		tfDescricao = new JTextField();
		tfDescricao.setBounds(239, 7, 427, 19);
		panel.add(tfDescricao);
		tfDescricao.setColumns(10);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(10, 37, 44, 13);
		panel.add(lblStatus);
		
		JComboBox cbStatus = new JComboBox();
		cbStatus.setBounds(64, 33, 96, 21);
		panel.add(cbStatus);
		
		JLabel lblCdBarras = new JLabel("Cód. barras");
		lblCdBarras.setBounds(170, 37, 69, 13);
		panel.add(lblCdBarras);
		
		tfCodBarras = new JTextField();
		tfCodBarras.setBounds(239, 34, 427, 19);
		panel.add(tfCodBarras);
		tfCodBarras.setColumns(10);
		
		JButton btnNewButton = new JButton("Pesquisar");
		btnNewButton.setBounds(570, 63, 96, 30);
		panel.add(btnNewButton);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(464, 63, 96, 30);
		panel.add(btnLimpar);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(10, 122, 96, 30);
		getContentPane().add(btnAdicionar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(116, 122, 96, 30);
		getContentPane().add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(222, 122, 96, 30);
		getContentPane().add(btnExcluir);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBounds(590, 392, 96, 30);
		getContentPane().add(btnFechar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 162, 676, 220);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBorder(new LineBorder(Color.BLACK));
		scrollPane.setViewportView(table);

	}
}
