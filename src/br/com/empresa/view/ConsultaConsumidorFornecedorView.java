package br.com.empresa.view;

import java.awt.Color;
import java.awt.Dimension;
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

public class ConsultaConsumidorFornecedorView extends JDialog {

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
	public ConsultaConsumidorFornecedorView() {

		serviceBeanLocal = new ServicoBeanLocal();

		setTitle("Manutenção de Consumidor / Fornecedor");
		setBounds(100, 100, 607, 378);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 578, 105);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblTipoPessoa = new JLabel("Tipo pessoa");
		lblTipoPessoa.setBounds(10, 11, 81, 14);
		panel.add(lblTipoPessoa);

		cbPessoa = new JComboBox();
		cbPessoa.setModel(new DefaultComboBoxModel(TipoPessoaEnum.values()));
		cbPessoa.insertItemAt(null, 0);
		cbPessoa.setSelectedIndex(0);
		cbPessoa.setBounds(89, 7, 92, 22);
		panel.add(cbPessoa);

		cbPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarTipoPessoa();
			}
		});

		lblCpfCnpj = new JLabel("CPF");
		lblCpfCnpj.setBounds(10, 39, 30, 14);
		panel.add(lblCpfCnpj);

		ftfCpfCnpj = new JFormattedTextField();
		ftfCpfCnpj.setBounds(45, 36, 136, 20);
		ftfCpfCnpj.setFocusLostBehavior(JFormattedTextField.PERSIST);

		panel.add(ftfCpfCnpj);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(190, 11, 46, 14);
		panel.add(lblNome);

		tfNome = new JTextField();
		tfNome.setBounds(232, 8, 327, 20);
		panel.add(tfNome);
		tfNome.setColumns(10);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(190, 39, 46, 14);
		panel.add(lblEstado);

		cbEstado = new JComboBox();
		cbEstado.setModel(new DefaultComboBoxModel(EstadoEnum.values()));
		cbEstado.insertItemAt(null, 0);
		cbEstado.setSelectedIndex(0);
		cbEstado.setBounds(232, 35, 136, 22);
		panel.add(cbEstado);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(373, 39, 40, 14);
		panel.add(lblCidade);

		tfCidade = new JTextField();
		tfCidade.setBounds(414, 36, 145, 20);
		panel.add(tfCidade);
		tfCidade.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnPesquisar.setBounds(459, 67, 100, 23);
		panel.add(btnPesquisar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(365, 67, 89, 23);
		panel.add(btnLimpar);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setBounds(10, 124, 89, 23);
		getContentPane().add(btnAdicionar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		btnEditar.setBounds(102, 124, 89, 23);
		getContentPane().add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		btnExcluir.setBounds(196, 124, 89, 23);
		getContentPane().add(btnExcluir);

		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnFechar.setBounds(492, 305, 89, 23);
		getContentPane().add(btnFechar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 158, 571, 137);
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

		alterarTipoPessoa();

		// Coloca a janela no centro da tela.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width /
				2, dim.height / 2 - this.getSize().height / 2);
	}
	
	private void adicionar() {
		
		ConsumidorFornecedorView cfv = new ConsumidorFornecedorView(this);
		cfv.setVisible(true);
		
	}
	
	private void editar() {
		
		if(table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, 
					"É necessário selecionar um registro!",
					"Mensagem de aviso",
					JOptionPane.WARNING_MESSAGE);
		}else {
			
			ConsumidorFornecedorView cfv = 
					new ConsumidorFornecedorView(this);
			
			PessoaVO aux = (PessoaVO)tableModel.getRows().get(table.getSelectedRow()).getElement();
			
			cfv.editar(aux);
			
			cfv.setVisible(true);
		}
		
		
	}

	private void fechar() {
		this.setVisible(false);
		this.dispose();
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

	private void limpar() {

		tfCidade.setText(null);
		tfNome.setText(null);
		cbEstado.setSelectedIndex(0);
		cbPessoa.setSelectedIndex(0);
		ftfCpfCnpj.setText(null);
		alterarTipoPessoa();
		pesquisar();

	}

	public void pesquisar() {

		TableModel tableModel = (TableModel) table.getModel();
		tableModel.clearTable();

		try {

			TipoPessoaEnum tipoPessoaEnum = (TipoPessoaEnum) cbPessoa.getSelectedItem();
			EstadoEnum estadoEnum = (EstadoEnum) cbEstado.getSelectedItem();

			String tipoPessoa = null;
			if (tipoPessoaEnum != null) {
				tipoPessoa = tipoPessoaEnum.name();
			}

			String estado = null;
			if (estadoEnum != null) {
				estado = estadoEnum.name();
			}

			List<PessoaVO> pessoas = serviceBeanLocal.listarPessoas(tipoPessoa, tfNome.getText(), ftfCpfCnpj.getText(),
					tfCidade.getText(), estado, Dados.getClienteSelecionado());

			for (PessoaVO p : pessoas) {

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
			JOptionPane.showMessageDialog(this, e.getMessage(), "Mensagem de aviso", JOptionPane.WARNING_MESSAGE);
		} catch (BOException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao executar a operação", "Mensagem de erro",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void alterarTipoPessoa() {

		TipoPessoaEnum tipoPessoaEnum = (TipoPessoaEnum) cbPessoa.getSelectedItem();

		if (tipoPessoaEnum == null || tipoPessoaEnum.name().equals("F")) {

			lblCpfCnpj.setText("CPF");
			String formatString = "#########-##";
			MascaraJFormattedTextField.formatField(formatString, ftfCpfCnpj);

		} else if (tipoPessoaEnum.name().equals("J")) {
			lblCpfCnpj.setText("CNPJ");
			String formatString = "##.###.###/####-##";
			MascaraJFormattedTextField.formatField(formatString, ftfCpfCnpj);
		}

	}
}
