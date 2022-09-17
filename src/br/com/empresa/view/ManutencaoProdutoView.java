package br.com.empresa.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.empresa.dao.Dados;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.service.IServicoBeanLocal;
import br.com.empresa.service.ServicoBeanLocal;
import br.com.empresa.view.util.MascaraJFormattedTextField;
import br.com.empresa.vo.PessoaVO;
import br.com.empresa.vo.ProdutoVO;
import br.com.empresa.vo.enums.EstadoEnum;
import br.com.empresa.vo.enums.StatusEnum;
import br.com.empresa.vo.enums.TipoPessoaEnum;
import javax.swing.JFormattedTextField;

public class ManutencaoProdutoView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfCodigo;
	private JTextField tfDescricao;
	private JTextField tfCdBarras;
	private JFormattedTextField tfQtdEstoque;
	private JFormattedTextField tfVlrCompra;
	private JFormattedTextField tfVlrVenda;
	private JComboBox cbStatus;
	
	private ProdutoVO produtoVO;
	
	private IServicoBeanLocal servicoBeanLocal;
	
	private ConsultaManutencaoProdutoView telaAnterior;
	
	public ManutencaoProdutoView(ConsultaManutencaoProdutoView jDialog) {
		super(jDialog, true);
		inicializarComponentes();
		telaAnterior = jDialog;
	}
	/**
	 * Create the dialog.
	 * @return 
	 */
	public ManutencaoProdutoView() {
		inicializarComponentes();
	}
	
	public void inicializarComponentes() {
		
		servicoBeanLocal = new ServicoBeanLocal();
		produtoVO = new ProdutoVO();
		
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
		
		JLabel lblQtdEstoque = new JLabel("Qtd. Estoque: *");
		lblQtdEstoque.setBounds(10, 100, 87, 13);
		contentPanel.add(lblQtdEstoque);
		
		JLabel lblVlrCompra = new JLabel("Vlr. Compra: *");
		lblVlrCompra.setBounds(10, 130, 87, 13);
		contentPanel.add(lblVlrCompra);
		
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
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
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
		
		tfQtdEstoque = new JFormattedTextField();
		tfQtdEstoque.setBounds(107, 97, 69, 19);
		tfQtdEstoque.setFocusLostBehavior(JFormattedTextField.PERSIST);
		contentPanel.add(tfQtdEstoque);
		
		tfVlrCompra = new JFormattedTextField();
		tfVlrCompra.setBounds(107, 127, 110, 19);
		tfVlrCompra.setFocusLostBehavior(JFormattedTextField.PERSIST);
		contentPanel.add(tfVlrCompra);
		
		tfVlrVenda = new JFormattedTextField();
		tfVlrVenda.setBounds(107, 157, 110, 19);
		tfVlrVenda.setFocusLostBehavior(JFormattedTextField.PERSIST);
		contentPanel.add(tfVlrVenda);
		
	}
	
	private void salvar() {
		
		String vlrcom = tfVlrCompra.getText().trim().replaceAll(",", ".").replaceAll(" ", "");
		if(vlrcom.length() > 1) {
			BigDecimal vlrCompra = new BigDecimal(vlrcom);
			produtoVO.setValcom(vlrCompra);
		}
		
		String vlrVen = tfVlrVenda.getText().trim().replaceAll(",", ".").replaceAll(" ", "");
		if(vlrVen.length() > 1) {
			BigDecimal vlrVenda = new BigDecimal(vlrVen);
			produtoVO.setValven(vlrVenda);
		}
		
		
		String qtdEstqStr = tfQtdEstoque.getText().trim().replaceAll(",", ".").replaceAll(" ", "");
		if(qtdEstqStr.length() > 1) {
			BigDecimal qtd = new BigDecimal(qtdEstqStr);
			produtoVO.setQtdest(qtd);
		}

		produtoVO.setDescri(tfDescricao.getText());
		produtoVO.setCodbar(tfCdBarras.getText());
		produtoVO.setClient(Dados.getClienteSelecionado());
		StatusEnum sp = (StatusEnum) cbStatus.getSelectedItem();
		
		if(sp != null) {
			produtoVO.setStatus(sp.name());
		}
		
		try {
			
			servicoBeanLocal.salvarProduto(produtoVO);
			
			tfCodigo.setText(produtoVO.getId().toString());
			
			telaAnterior.pesquisar();
			
			JOptionPane.showMessageDialog(this, "Operação realizada com sucesso!",
					"Mensagem de comfirmação", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (BOValidationException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Mensagem de alerta",
					JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		} catch (BOException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação",
					"Mensagem de erro",
					JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		
	}
	
	public void editar(ProdutoVO produtoVO) {
		
		this.produtoVO = produtoVO;
		this.tfCodigo.setText(produtoVO.getId().toString());
		this.cbStatus.setSelectedItem(StatusEnum.valueOf(produtoVO.getStatus()));
		this.tfDescricao.setText(produtoVO.getDescri());
		this.tfCdBarras.setText(produtoVO.getCodbar());
		this.tfQtdEstoque.setText(produtoVO.getQtdest().toString());
		this.tfVlrCompra.setText(produtoVO.getValcom().toString());
		this.tfVlrVenda.setText(produtoVO.getValven().toString());
		
	}
	
	private void fechar() {
		this.setVisible(false);
		this.dispose();
	}
}
