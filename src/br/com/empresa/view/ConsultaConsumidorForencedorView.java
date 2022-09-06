package br.com.empresa.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumnModel;

import br.com.empresa.dao.Dados;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.service.IServicoBeanLocal;
import br.com.empresa.service.ServicoBeanLocal;
import br.com.empresa.view.util.MascaraJFormattedTextField;
import br.com.empresa.view.util.RowData;
import br.com.empresa.view.util.TableModel;
import br.com.empresa.vo.PessoaVO;
import br.com.empresa.vo.enums.EstadoEnum;
import br.com.empresa.vo.enums.TipoPessoaEnum;

public class ConsultaConsumidorForencedorView extends JDialog {
	private JTextField tfNome;
	private JTextField tfCidade;
	private JTable table;
	private JComboBox cbPessoa;
	private JComboBox cbEstado;
	private JFormattedTextField ftfCpfCnpj;
	private JLabel lblCpfCnpj;
	private TableModel tableModel;
	
	private IServicoBeanLocal serviceBeanLocal;
	/**
	 * Create the dialog.
	 */
	public ConsultaConsumidorForencedorView() {
		
		serviceBeanLocal = new ServicoBeanLocal();
		
		setTitle("Manutenção de Consumidor / Fornecedor");
		setBounds(100, 100, 780, 493);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLACK));
		panel.setBounds(10, 10, 746, 126);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblTipoPessoa = new JLabel("Tipo Pessoa:");
		lblTipoPessoa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipoPessoa.setBounds(10, 10, 81, 21);
		panel.add(lblTipoPessoa);
		
		cbPessoa = new JComboBox();
		
		
		cbPessoa.setModel(new DefaultComboBoxModel(TipoPessoaEnum.values()));
		cbPessoa.insertItemAt(null, 0);
		cbPessoa.setSelectedIndex(0);
		cbPessoa.setBounds(87, 11, 101, 21);
		panel.add(cbPessoa);
		
		lblCpfCnpj = new JLabel("CPF");
		lblCpfCnpj.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCpfCnpj.setBounds(10, 52, 81, 21);
		panel.add(lblCpfCnpj);
		
		ftfCpfCnpj = new JFormattedTextField();
		ftfCpfCnpj.setBounds(47, 54, 141, 19);
		ftfCpfCnpj.setFocusLostBehavior(JFormattedTextField.PERSIST);
		panel.add(ftfCpfCnpj);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setBounds(207, 10, 38, 21);
		panel.add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setBounds(255, 12, 481, 19);
		panel.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEstado.setBounds(207, 52, 42, 21);
		panel.add(lblEstado);
		
		cbEstado = new JComboBox();
		cbEstado.setModel(new DefaultComboBoxModel(EstadoEnum.values()));
		cbEstado.insertItemAt(null, 0);
		cbEstado.setSelectedIndex(0);
		cbEstado.setBounds(255, 53, 132, 21);
		panel.add(cbEstado);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCidade.setBounds(397, 52, 48, 21);
		panel.add(lblCidade);
		
		tfCidade = new JTextField();
		tfCidade.setBounds(448, 54, 288, 19);
		panel.add(tfCidade);
		tfCidade.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnPesquisar.setBounds(642, 83, 94, 32);
		panel.add(btnPesquisar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(538, 83, 94, 32);
		panel.add(btnLimpar);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(10, 146, 94, 32);
		getContentPane().add(btnAdicionar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(114, 146, 94, 32);
		getContentPane().add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		btnExcluir.setBounds(218, 146, 94, 32);
		getContentPane().add(btnExcluir);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnFechar.setBounds(662, 414, 94, 32);
		getContentPane().add(btnFechar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 188, 746, 219);
		getContentPane().add(scrollPane);
		
		tableModel = new TableModel();
		tableModel.addColumn("Código");
		tableModel.addColumn("CPF / CNPJ");
		tableModel.addColumn("Nome");
		tableModel.addColumn("Cidade");
		tableModel.addColumn("Estado");
		
		table = new JTable(tableModel);
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumnModel tableColumnModel = table.getColumnModel();
		tableColumnModel.getColumn(0).setPreferredWidth(50);
		tableColumnModel.getColumn(1).setPreferredWidth(150);
		tableColumnModel.getColumn(2).setPreferredWidth(230);
		tableColumnModel.getColumn(3).setPreferredWidth(100);
		tableColumnModel.getColumn(4).setPreferredWidth(150);
		
		pesquisar();
		
		scrollPane.setViewportView(table);

		cbPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarTipoPessoa();
			}
		});
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2,
				dim.height / 2 - this.getSize().height / 2);
		
	}
	
	private void alterarTipoPessoa() {
		
		TipoPessoaEnum tipoPessoaEnum = (TipoPessoaEnum) cbPessoa.getSelectedItem();
		
		if(tipoPessoaEnum == null || tipoPessoaEnum.name().equals("F")) {
			
			lblCpfCnpj.setText("CPF");
			String formatString = "#########-##";
			MascaraJFormattedTextField.formatField(formatString, ftfCpfCnpj);
			
		} else if(tipoPessoaEnum.name().equals("J")) {
			lblCpfCnpj.setText("CNPJ");
			String formatString = "##.###.###/####-##";
			MascaraJFormattedTextField.formatField(formatString, ftfCpfCnpj);
		}
	}
	
	private void pesquisar() {
		
		TableModel tableModel = (TableModel) table.getModel();
		tableModel.clearTable();
		
		try {
			
			TipoPessoaEnum tipoPessoaEnum = (TipoPessoaEnum) cbPessoa.getSelectedItem();
			EstadoEnum estadoEnum = (EstadoEnum) cbEstado.getSelectedItem();
			
			String tipoPessoa = null;
			if(tipoPessoaEnum != null) {
				tipoPessoa = tipoPessoaEnum.name();
			}
			
			String estado = null;
			if(estadoEnum != null) {
				estado = estadoEnum.name();
			}
			
			List<PessoaVO> pessoas = serviceBeanLocal.listarPessoas(tipoPessoa, 
					tfNome.getText(),
					ftfCpfCnpj.getText(),
					tfCidade.getText(),
					estado,
					Dados.getClienteSelecionado());
			
			for(PessoaVO p : pessoas) {
				
				RowData rowData = new RowData();
				rowData.getValues().put(0, p.getId().toString());
				rowData.getValues().put(1, p.getCpfcnp());
				rowData.getValues().put(2, p.getDescri());
				rowData.getValues().put(3, p.getCidade());
				rowData.getValues().put(4, p.getEstado());
				rowData.setElement(p);
				tableModel.addRow(rowData);
			}
			
		} catch (BOValidationException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Mensagem de aviso",
					JOptionPane.WARNING_MESSAGE);
		} catch (BOException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao executar a operação",
					"Mensagem de erro",
					JOptionPane.WARNING_MESSAGE);
		}
		
	}
	
	private void fechar() {	
		
		super.setVisible(false);
		super.dispose();
		
	}
	
	private void limpar() {
		cbPessoa.setSelectedIndex(0);
		cbEstado.setSelectedIndex(0);
		tfNome.setText(null);;
		ftfCpfCnpj.setText(null);
		tfCidade.setText(null);
		alterarTipoPessoa();
		pesquisar();
	}
	
	private void excluir() {

		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "É necessário selecionar um registro!", "Mensagem de aviso!",
					JOptionPane.WARNING_MESSAGE);
		} else {

			Object[] options = { "Sim", "Não" };
			int n = JOptionPane.showOptionDialog(null, "Deseja realmente excluir o registro?", "Confirmação",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			if (n == 0) {
				PessoaVO pessoaVO = (PessoaVO) tableModel.getRows().get(table.getSelectedRow()).getElement();

				try {
					serviceBeanLocal.excluirPessoa(pessoaVO);
					pesquisar();
				} catch (BOException e) {
					JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação!", "Mensagem de erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
