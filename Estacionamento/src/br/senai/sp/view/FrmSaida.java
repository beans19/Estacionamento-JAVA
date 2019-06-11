package br.senai.sp.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.senai.sp.dao.ClienteDAO;
import br.senai.sp.model.Calculos;
import br.senai.sp.model.Cliente;
import br.senai.sp.utils.Data;
import br.senai.sp.view.FrmPrincipal;;

public class FrmSaida extends JDialog {

	public JLabel lblIcone;
	public JLabel lblTitulo;
	public JLabel lblCodigo;
	public JLabel lblCodValue;
	public JLabel lblPlaca;
	public JTextField txtPlaca;
	public JLabel lblDtHrEntrada;
	public JTextField txtDtHrEntrada;
	public JLabel lblDtHrSaida;
	public JTextField txtDtHrSaida;
	public JLabel lblTempo;
	public JTextField txtTempo;
	public JLabel lblValorPagar;
	public JTextField txtValorPagar;
	public JLabel lblModelo;
	public JTextField txtModelo;
	public JButton btBotao;

	private Color branco = new Color(255, 255, 255);
	private Color azul = new Color(0, 0, 255);
	private Font titulo = new Font("Arial", 1, 30);
	private Font texto = new Font("Arial", 1, 20);

	public FrmSaida() {
		
		// DEFINIR INFORMAÇÕES DA JANELA
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Saída");
		setSize(400, 800);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		// PANEL TITULO
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(0, 0, 400, 400);
		panelTitulo.setLayout(null);
		panelTitulo.setSize(630, 60);
		panelTitulo.setBackground(azul);
		
		// COMPONENTES DO PANEL TITULO
		lblIcone = new JLabel();
		lblIcone.setBounds(12, 5, 50, 50);
		lblIcone.setIcon(new ImageIcon(FrmEntrada.class.getResource("/br/senai/sp/imagens/edit32.png")));
		
		lblTitulo = new JLabel("Saída de Veículo");
		lblTitulo.setForeground(branco);
		lblTitulo.setFont(titulo);
		lblTitulo.setBounds(62, 15, 300, 30);
		

		// PAINEL COMPONENTES
		JPanel panelComponentes = new JPanel();
		panelComponentes.setLayout(null);
		panelComponentes.setBounds(0, 50, 400, 800);

		// COMPONENTES DA TELA
		lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(250, 0, 75, 100);
		lblCodigo.setFont(texto);

		lblCodValue = new JLabel();
		lblCodValue.setBounds(330, 0, 50, 100);
		lblCodValue.setFont(texto);

		lblPlaca = new JLabel("Placa:");
		lblPlaca.setBounds(10, 0, 100, 100);
		lblPlaca.setFont(texto);

		txtPlaca = new JTextField();
		txtPlaca.setBounds(10, 70, 200, 30);

		lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(10, 90, 100, 100);
		lblModelo.setFont(texto);

		txtModelo = new JTextField();
		txtModelo.setBounds(10, 160, 200, 30);

		lblDtHrEntrada = new JLabel("Data e Hora de Entrada:");
		lblDtHrEntrada.setBounds(10, 180, 400, 100);
		lblDtHrEntrada.setFont(texto);

		txtDtHrEntrada = new JTextField();
		txtDtHrEntrada.setBounds(10, 250, 200, 30);

		lblDtHrSaida = new JLabel("Data e Hora de Saída:");
		lblDtHrSaida.setBounds(10, 270, 400, 100);
		lblDtHrSaida.setFont(texto);

		txtDtHrSaida = new JTextField();
		txtDtHrSaida.setBounds(10, 340, 200, 30);

		lblTempo = new JLabel("Tempo (Horas):");
		lblTempo.setBounds(10, 360, 200, 100);
		lblTempo.setFont(texto);

		txtTempo = new JTextField();
		txtTempo.setBounds(10, 430, 200, 30);

		lblValorPagar = new JLabel("Valor a Pagar:");
		lblValorPagar.setBounds(10, 450, 400, 100);
		lblValorPagar.setFont(texto);

		txtValorPagar = new JTextField();
		txtValorPagar.setBounds(10, 520, 200, 30);

		// BOTAO ATUALIZAR, REMOVER E GRAVAR
		btBotao = new JButton("Confirmar Saída");
		btBotao.setBounds(10, 580, 170, 50);
		btBotao.setIcon(new ImageIcon(FrmEntrada.class.getResource("/br/senai/sp/imagens/delete32.png")));

		// AÇÃO DO BOTÃO
		btBotao.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Cliente cliente = new Cliente();

				cliente.setPlaca(txtPlaca.getText());
				cliente.setModelo(txtModelo.getText());
				cliente.setCodigo(Integer.parseInt(lblCodValue.getText()));

				ClienteDAO dao = new ClienteDAO(cliente);

				if (btBotao.getText().equals("Atualizar")) {
					cliente.setData_entrada(Data.converterParaBanco(txtDtHrEntrada.getText()));
					dao.atualizar();
					JOptionPane.showMessageDialog(null, "Veículo Atualizado com Sucesso!", "Atualizar Veículo",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (btBotao.getText().equals("Remover")) {
					cliente.setData_entrada(Data.converterParaBanco(txtDtHrEntrada.getText()));
					cliente.setData_saida(Data.converterParaBanco(Data.horaAtual()));
					cliente.setTempo(Integer.parseInt(txtTempo.getText()));
					cliente.setValor_pago(Double.parseDouble(txtValorPagar.getText()));
					JOptionPane.showMessageDialog(null, "Veículo Removido com Sucesso!", "Remover Veículo",
							JOptionPane.INFORMATION_MESSAGE);
					dao.atualizar();
				} else if (btBotao.getText().equals("Gravar")){
					dao.gravar();
					JOptionPane.showMessageDialog(null, "Veículo Gravado com Sucesso!", "Novo Veículo",
							JOptionPane.INFORMATION_MESSAGE);
				}

				btBotao.setEnabled(false);

			}

		});

		// INSERINDO OBJETOS NOS PAINÉIS
		panelTitulo.add(lblTitulo);
		panelTitulo.add(lblIcone);
		panelComponentes.add(lblPlaca);
		panelComponentes.add(txtPlaca);
		panelComponentes.add(lblModelo);
		panelComponentes.add(txtModelo);
		panelComponentes.add(btBotao);
		panelComponentes.add(lblDtHrEntrada);
		panelComponentes.add(txtDtHrEntrada);
		panelComponentes.add(lblDtHrSaida);
		panelComponentes.add(txtDtHrSaida);
		panelComponentes.add(lblTempo);
		panelComponentes.add(lblValorPagar);
		panelComponentes.add(txtTempo);
		panelComponentes.add(txtValorPagar);
		panelComponentes.add(lblCodigo);
		panelComponentes.add(lblCodValue);

		// POSICIONAR COMPONENTES NA TELA
		getContentPane().add(panelTitulo);
		getContentPane().add(panelComponentes);

		setModal(true);
	}
}
