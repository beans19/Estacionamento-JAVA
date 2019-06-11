package br.senai.sp.view;

import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import br.senai.sp.dao.ClienteDAO;
import br.senai.sp.model.Calculos;
import br.senai.sp.model.Cliente;
import br.senai.sp.utils.Data;

public class FrmPrincipal extends JFrame {

	public JTable tabelaCarros;
	public JPanel painelTabela;
	public JScrollPane scroll;

	public FrmPrincipal() {

		// DEFINIR INFORMAÇÕES DA JANELA
		setTitle("Estacionamento");
		setSize(735, 550);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		// CRIAR PAINEL DA TABELA
		painelTabela = new JPanel();
		painelTabela.setBounds(10, 10, 700, 400);
		painelTabela.setLayout(null);
		TitledBorder bordaTabela = new TitledBorder("Lista de clientes:");
		bordaTabela.setTitleColor(new Color(0, 0, 255));
		painelTabela.setBorder(bordaTabela);

		// CRIAR PAINEL DOS BOTÕES
		JPanel painelBotoes = new JPanel();
		TitledBorder bordaBotoes = new TitledBorder("");
		painelBotoes.setBounds(12, 420, 700, 80);
		painelBotoes.setLayout(null);
		painelBotoes.setBorder(bordaBotoes);

		// CRIAR OS BOTÕES
		JButton btNovo = new JButton();
		btNovo.setBounds(10, 10, 70, 60);
		btNovo.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/br/senai/sp/imagens/novo32.png")));
		btNovo.setToolTipText("Adicionar novo veículo");

		JButton btExcluir = new JButton();
		btExcluir.setBounds(90, 10, 70, 60);
		btExcluir.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/br/senai/sp/imagens/delete32.png")));
		btExcluir.setToolTipText("Excluir o veículo selecionado");

		JButton btEditar = new JButton();
		btEditar.setBounds(170, 10, 70, 60);
		btEditar.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/br/senai/sp/imagens/edit32.png")));
		btEditar.setToolTipText("Atualizar");

		JButton btSair = new JButton();
		btSair.setBounds(620, 10, 70, 60);
		btSair.setToolTipText("Sair do sistema");
		btSair.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/br/senai/sp/imagens/sair32.png")));

		// ADICIONAR BOTÕES AO PAINEL BOTÕES
		painelBotoes.add(btNovo);
		painelBotoes.add(btExcluir);
		painelBotoes.add(btEditar);
		painelBotoes.add(btSair);

		// CRIAR UM SCROLL PARA A TABELA
		scroll = new JScrollPane();
		scroll.setBounds(10, 30, 682, 360);

		// PARA PREENCHER O JTABLE
		String[] cabecalho = { "CÓD", "PLACA", "MODELO", "DATA ENTRADA", "DATA SAÍDA", "TEMPO", "VALOR PAGO" };

		ClienteDAO dao = new ClienteDAO();
		ArrayList<Cliente> clientes = dao.listarClientesEstacionados();

		int linhas = clientes.size();

		String[][] dados = new String[linhas][7];

		int linha = 0;

		for (Cliente cliente : clientes) {
			dados[linha][0] = String.valueOf(cliente.getCodigo());
			dados[linha][1] = cliente.getPlaca();
			dados[linha][2] = cliente.getModelo();
			dados[linha][3] = cliente.getData_entrada();
			dados[linha][4] = String.valueOf(cliente.getData_saida());
			dados[linha][5] = String.valueOf(cliente.getTempo());
			dados[linha][6] = String.valueOf(cliente.getValor_pago());
			linha++;
		}

		// DEFINIR O MODELO PARA A JTABLE
		DefaultTableModel modelo = new DefaultTableModel(dados, cabecalho);

		// CRIAR A TABELA (JTABLE)
		tabelaCarros = new JTable();
		tabelaCarros.setModel(modelo);

		// DETERMINAR A LARGURA DAS COLUNAS DA TABELA
		tabelaCarros.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabelaCarros.getColumnModel().getColumn(0).setPreferredWidth(50);
		tabelaCarros.getColumnModel().getColumn(1).setPreferredWidth(70);
		tabelaCarros.getColumnModel().getColumn(2).setPreferredWidth(100);
		tabelaCarros.getColumnModel().getColumn(3).setPreferredWidth(150);
		tabelaCarros.getColumnModel().getColumn(4).setPreferredWidth(150);
		tabelaCarros.getColumnModel().getColumn(5).setPreferredWidth(60);
		tabelaCarros.getColumnModel().getColumn(6).setPreferredWidth(100);

		// IMPEDIR EDIÇÃO DA TABELA
		tabelaCarros.setDefaultEditor(Object.class, null);

		// IMPEDIR DRAG AND DROP (ARRASTAR) DAS COLUNAS DA TABELA
		tabelaCarros.getTableHeader().setReorderingAllowed(false);

		// IMPEDIR O REDIMENSIONAMENTO DAS COLUNAS DA TABELA
		tabelaCarros.getTableHeader().setResizingAllowed(false);

		// COLOCAR A TABELA DENTRO DO SCROLL
		scroll.setViewportView(tabelaCarros);

		// COLOCAR O SCROLL DENTRO DO PAINEL
		painelTabela.add(scroll);

		// POSICIONAR COMPONENTES NA TELA
		getContentPane().add(painelBotoes);
		getContentPane().add(painelTabela);

		setVisible(true);

		// OUVINTES DE AÇÃO

		// BOTÃO PARA CRIAR UM NOVO VEÍCULO
		btNovo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FrmEntrada frmEntrada = new FrmEntrada();
				frmEntrada.setVisible(true);
			}
		});

		// BOTÃO PARA REMOVER UM VEÍCULO
		btExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = new Cliente();

				cliente.setData_saida(Data.horaAtual());
//				cliente.setTempo(Data.quantidadeHoras(cliente.getData_entrada(), cliente.getData_saida()));
				abrirFormularioCarro("Saída de Veículo", "Remover", "delete32.png");
			}
		});

		// BOTÃO PARA EDITAR UM VEÍCULO
		btEditar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				abrirFormularioCarro("Editar Veículo", "Atualizar", "edit32.png");
			}
		});

		// BOTÃO PARA SAIR DO SISTEMA
		btSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int resposta;

				resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Fechar Sistema",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (resposta == 0) {
					System.exit(EXIT_ON_CLOSE);
				}
			}
		});

		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
				criarTabela();
			}
		});
		setVisible(true);
	}

	// MÉTODO QUE CRIA A TABELA
	private void criarTabela() {

		String[] cabecalho = { "CÓD", "PLACA", "MODELO", "DATA ENTRADA", "DATA SAÍDA", "TEMPO", "VALOR PAGO" };

		ClienteDAO dao = new ClienteDAO();
		ArrayList<Cliente> clientes = dao.listarClientesEstacionados();

		int linhas = clientes.size();

		String[][] dados = new String[linhas][7];

		int linha = 0;

		for (Cliente cliente : clientes) {
			dados[linha][0] = String.valueOf(cliente.getCodigo());
			dados[linha][1] = cliente.getPlaca();
			dados[linha][2] = cliente.getModelo();
			dados[linha][3] = Data.converterParaTela(cliente.getData_entrada());
			dados[linha][4] = String.valueOf(cliente.getData_saida());
			dados[linha][5] = String.valueOf(cliente.getTempo());
			dados[linha][6] = String.valueOf(cliente.getValor_pago());
			linha++;
		}

		DefaultTableModel modelo = new DefaultTableModel(dados, cabecalho);

		tabelaCarros = new JTable();
		tabelaCarros.setModel(modelo);

		tabelaCarros.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabelaCarros.getColumnModel().getColumn(0).setPreferredWidth(50);
		tabelaCarros.getColumnModel().getColumn(1).setPreferredWidth(70);
		tabelaCarros.getColumnModel().getColumn(2).setPreferredWidth(100);
		tabelaCarros.getColumnModel().getColumn(3).setPreferredWidth(150);
		tabelaCarros.getColumnModel().getColumn(4).setPreferredWidth(150);
		tabelaCarros.getColumnModel().getColumn(5).setPreferredWidth(60);
		tabelaCarros.getColumnModel().getColumn(6).setPreferredWidth(100);

		tabelaCarros.setDefaultEditor(Object.class, null);

		tabelaCarros.getTableHeader().setReorderingAllowed(false);

		tabelaCarros.getTableHeader().setResizingAllowed(false);

		scroll.setViewportView(tabelaCarros);

		painelTabela.add(scroll);

	}

	private void abrirFormularioCarro(String textoDoTitulo, String textoDoBotao, String nomeDaImagem) {

		// ONTER O CODIGO DO VEÍCULO
		int linha = tabelaCarros.getSelectedRow();
		int coluna = 0;

		int codigo = 0;

		if (linha == -1) {
			JOptionPane.showMessageDialog(null, "Por favor, selecione um veículo na lista", "Veículo não selecionado",
					JOptionPane.ERROR_MESSAGE);
		} else {
			codigo = Integer.parseInt(tabelaCarros.getValueAt(linha, coluna).toString());

			// CRIAR UM NOVO VEÍCULO COM OS DADOS DO BANCO
			Cliente cliente = new Cliente();

			ClienteDAO dao = new ClienteDAO();
			cliente = dao.getCliente(codigo);

			// ABRIR O FORMULÁRIO SAÍDA CARREGANDO OS DADOS DO BANCO
			FrmSaida saida = new FrmSaida();

			saida.lblTitulo.setText(textoDoTitulo);
			saida.btBotao.setText(textoDoBotao);
			saida.btBotao.setIcon(new ImageIcon(FrmSaida.class.getResource("/br/senai/sp/imagens/" + nomeDaImagem)));
			saida.txtPlaca.setText(cliente.getPlaca());
			saida.lblCodValue.setText(String.valueOf(cliente.getCodigo()));
			saida.txtDtHrSaida.setText(Data.horaAtual());
			saida.txtModelo.setText(cliente.getModelo());
			saida.txtDtHrEntrada.setText(Data.converterParaTela(cliente.getData_entrada()));
			saida.txtTempo.setText(String
					.valueOf(Data.quantidadeHoras(String.valueOf(Data.converterParaTela(cliente.getData_entrada())),
							String.valueOf(Data.horaAtual()))));
			cliente.setTempo(Data.quantidadeHoras(String.valueOf(Data.converterParaTela(cliente.getData_entrada())),
					String.valueOf(Data.horaAtual())));
			saida.txtValorPagar.setText(String.valueOf(Calculos.valorPagar(cliente.getTempo())));
			saida.lblIcone.setIcon(new ImageIcon(FrmSaida.class.getResource("/br/senai/sp/imagens/" + nomeDaImagem)));
			saida.lblTitulo.setForeground(new Color(255, 255, 255));

			// CONDIÇÃO SE O BOTÃO FOR PARA REMOVER O VEÍCULO
			if (textoDoBotao.equals("Remover")) {
				saida.lblTitulo.setForeground(new Color(255, 255, 255));
				saida.txtPlaca.setEnabled(false);
				saida.txtModelo.setEnabled(false);
				saida.txtDtHrEntrada.setEnabled(false);
				saida.txtDtHrSaida.setEnabled(false);
				saida.txtTempo.setEnabled(false);
				saida.txtValorPagar.setEnabled(false);

			// CONDIÇÃO SE O BOTÃO FOR PARA ATUALIZAR O VEÍCULO
			} else if (textoDoBotao.equals("Atualizar")) {
				saida.lblTitulo.setForeground(new Color(255, 255, 255));
				saida.txtDtHrEntrada.setEnabled(false);
				saida.txtDtHrSaida.setEnabled(false);
				saida.txtTempo.setEnabled(false);
				saida.txtValorPagar.setEnabled(false);
			}

			saida.setVisible(true);
		}
	}
}