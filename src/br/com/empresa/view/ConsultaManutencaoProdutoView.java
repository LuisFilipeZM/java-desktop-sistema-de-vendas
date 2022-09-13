package br.com.empresa.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.List;

import javax.swing.AbstractButton;
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
import br.com.empresa.view.util.RowData;
import br.com.empresa.view.util.TableModel;
import br.com.empresa.vo.ProdutoVO;
import br.com.empresa.vo.enums.StatusEnum;

public class ConsultaManutencaoProdutoView extends JDialog {

	private JTextField tfDescricao;
	private JTextField tfCodBarras;
	private JComboBox cbStatus;
	private JTable table;
	private JFormattedTextField ftfCodigo;
	private TableModel tableModel;

	private IServicoBeanLocal serviceBeanLocal;

	/**
	 * Create the dialog.
	 */
	public ConsultaManutencaoProdutoView() {

		serviceBeanLocal = new ServicoBeanLocal();

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

		cbStatus = new JComboBox();
		cbStatus.setModel(new DefaultComboBoxModel(StatusEnum.values()));
		cbStatus.insertItemAt(null, 0);
		cbStatus.setSelectedIndex(0);
		cbStatus.setBounds(64, 33, 96, 21);
		panel.add(cbStatus);

		JLabel lblCdBarras = new JLabel("Cód. barras");
		lblCdBarras.setBounds(170, 37, 69, 13);
		panel.add(lblCdBarras);

		tfCodBarras = new JTextField();
		tfCodBarras.setBounds(239, 34, 427, 19);
		panel.add(tfCodBarras);
		tfCodBarras.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnPesquisar.setBounds(570, 63, 96, 30);
		panel.add(btnPesquisar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(464, 63, 96, 30);
		panel.add(btnLimpar);

		ftfCodigo = new JFormattedTextField();
		ftfCodigo.setBounds(64, 6, 96, 20);
		panel.add(ftfCodigo);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adcionar();
			}
		});
		btnAdicionar.setBounds(10, 122, 96, 30);
		getContentPane().add(btnAdicionar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(116, 122, 96, 30);
		getContentPane().add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(222, 122, 96, 30);
		getContentPane().add(btnExcluir);

		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnFechar.setBounds(590, 392, 96, 30);
		getContentPane().add(btnFechar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 162, 676, 220);
		getContentPane().add(scrollPane);

		tableModel = new TableModel();
		tableModel.addColumn("Código");
		tableModel.addColumn("Descrição");
		tableModel.addColumn("Qtd. Estoque");
		tableModel.addColumn("Status");
		tableModel.addColumn("Vlr. Compra");
		tableModel.addColumn("Vlr. Venda");

		table = new JTable(tableModel);
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel tableColumnModel = table.getColumnModel();
		tableColumnModel.getColumn(0).setPreferredWidth(50);
		tableColumnModel.getColumn(1).setPreferredWidth(270);
		tableColumnModel.getColumn(2).setPreferredWidth(80);
		tableColumnModel.getColumn(3).setPreferredWidth(100);
		tableColumnModel.getColumn(4).setPreferredWidth(80);
		tableColumnModel.getColumn(5).setPreferredWidth(80);

		// Coloca a janela no centro da tela.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		pesquisar();

		scrollPane.setViewportView(table);
	}

	private void pesquisar() {

		TableModel tableModel = (TableModel) table.getModel();
		tableModel.clearTable();

		try {

			StatusEnum statusEnum = (StatusEnum) cbStatus.getSelectedItem();

			String status = null;
			if (statusEnum != null) {
				status = statusEnum.name();
			}

			String codbar = null;
			if (tfCodBarras != null && tfCodBarras.getText().trim().length() > 0) {
				codbar = tfCodBarras.getText();
			}

			BigInteger bigIntegerStr = null;
			if (ftfCodigo != null && ftfCodigo.getText().trim().length() > 0) {
				bigIntegerStr = new BigInteger(ftfCodigo.getText());
			}

			List<ProdutoVO> produto = serviceBeanLocal.listarProduto(bigIntegerStr, tfDescricao.getText(), status,
					codbar, Dados.getClienteSelecionado());

			for (ProdutoVO p : produto) {

				RowData rowData = new RowData();
				rowData.getValues().put(0, p.getId().toString());
				rowData.getValues().put(1, p.getDescri());
				rowData.getValues().put(2, p.getQtdest());
				rowData.getValues().put(3, p.getStatus());
				rowData.getValues().put(4, p.getValcom());
				rowData.getValues().put(5, p.getValven());
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

	private void adcionar() {

		ManutencaoProdutoView mpv = new ManutencaoProdutoView(this);
		mpv.setVisible(true);

	}

	private void limpar() {
		
		tfCodBarras.setText(null);
		tfDescricao.setText(null);
		ftfCodigo.setText(null);
		cbStatus.setSelectedIndex(0);
		
	}

	private void fechar() {
		
		this.setVisible(false);
		this.dispose();
		
	}
}
