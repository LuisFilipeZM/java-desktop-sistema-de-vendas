package br.com.empresa.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.empresa.vo.ProdutoVO;
import br.com.empresa.vo.enums.StatusEnum;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManutencaoProdutoView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfCodigo;
	private JTextField tfDescricao;
	private JTextField tfCdBarras;
	private JTextField tfQtdEstoque;
	private JTextField tfVlrCompra;
	private JTextField tfVlrVenda;
	
	private JComboBox cbStatus;
	
	private ProdutoVO produtoVO;
	
	public ManutencaoProdutoView(ConsultaManutencaoProdutoView jDialog) {
		super(jDialog, true);
		inicializarComponentes();
	}
	/**
	 * Create the dialog.
	 * @return 
	 */
	public ManutencaoProdutoView() {
		inicializarComponentes();
	}
	
	public void inicializarComponentes() {
		setTitle("Manutenção de Produto <2>");
		setBounds(100, 100, 408, 296);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(10, 10, 87, 13);
		contentPanel.add(lblCodigo);
			
		tfCodigo = new JTextField();
		tfCodigo.setEditable(false);
		tfCodigo.setBounds(107, 7, 110, 19);
		contentPanel.add(tfCodigo);
		tfCodigo.setColumns(10);
		
		tfDescricao = new JTextField();
		tfDescricao.setColumns(10);
		tfDescricao.setBounds(107, 37, 275, 19);
		contentPanel.add(tfDescricao);
		
		JLabel lblDescricao = new JLabel("Descrição: *");
		lblDescricao.setBounds(10, 40, 87, 13);
		contentPanel.add(lblDescricao);
		
		tfCdBarras = new JTextField();
		tfCdBarras.setColumns(10);
		tfCdBarras.setBounds(107, 67, 171, 19);
		contentPanel.add(tfCdBarras);
		
		JLabel lblCdBarras = new JLabel("Cód. Barras: *");
		lblCdBarras.setBounds(10, 70, 87, 13);
		contentPanel.add(lblCdBarras);
		
		tfQtdEstoque = new JTextField();
		tfQtdEstoque.setColumns(10);
		tfQtdEstoque.setBounds(107, 97, 74, 19);
		contentPanel.add(tfQtdEstoque);
		
		JLabel lblQtdEstoque = new JLabel("Qtd. Estoque: *");
		lblQtdEstoque.setBounds(10, 100, 87, 13);
		contentPanel.add(lblQtdEstoque);
		
		tfVlrCompra = new JTextField();
		tfVlrCompra.setColumns(10);
		tfVlrCompra.setBounds(107, 127, 110, 19);
		contentPanel.add(tfVlrCompra);
		
		JLabel lblVlrCompra = new JLabel("Vlr. Compra: *");
		lblVlrCompra.setBounds(10, 130, 87, 13);
		contentPanel.add(lblVlrCompra);
		
		tfVlrVenda = new JTextField();
		tfVlrVenda.setColumns(10);
		tfVlrVenda.setBounds(107, 157, 110, 19);
		contentPanel.add(tfVlrVenda);
		
		JLabel lblVlrVenda = new JLabel("Vlr. Venda: *");
		lblVlrVenda.setBounds(10, 160, 87, 13);
		contentPanel.add(lblVlrVenda);
		
		cbStatus = new JComboBox();
		cbStatus.setModel(new DefaultComboBoxModel(StatusEnum.values()));
		cbStatus.insertItemAt(null, 0);
		cbStatus.setSelectedIndex(0);
		cbStatus.setBounds(107, 187, 110, 22);
		contentPanel.add(cbStatus);
		
		JLabel lblStatus = new JLabel("Status: *");
		lblStatus.setBounds(10, 191, 87, 13);
		contentPanel.add(lblStatus);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBounds(293, 223, 89, 23);
		contentPanel.add(btnFechar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		btnSalvar.setBounds(189, 223, 89, 23);
		contentPanel.add(btnSalvar);
	}
	
	private void salvar() {
		
		BigInteger bigIntegerStr = null;
		if (tfQtdEstoque != null && tfQtdEstoque.getText().trim().length() > 0) {
			bigIntegerStr = new BigInteger(tfQtdEstoque.getText());
		}
		
		produtoVO.setDescri(tfDescricao.getText());
		produtoVO.setCodbar(tfCdBarras.getText());
		StatusEnum sp = (StatusEnum) cbStatus.getSelectedItem();
		produtoVO.setStatus(sp.name());
		
	}
}
