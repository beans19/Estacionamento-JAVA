package br.senai.sp.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.senai.sp.dao.ClienteDAO;
import br.senai.sp.model.Cliente;
import br.senai.sp.utils.Data;

public class FrmEntrada extends JDialog {

	// ELEMENTOS
	public JLabel lblIcone;
	public JLabel lblTitulo;
	public JLabel lblPlaca;
	public JTextField txtPlaca;
	public JLabel lblModelo;
	public JTextField txtModelo;
	public JButton btGravar;

	// CORES & FONTES
	private Color azul = new Color(0, 0, 255);
	private Color branco = new Color(255, 255, 255);
	private Font titulo = new Font("Arial", 1, 30);
	private Font texto = new Font("Arial", 1, 20);

	public FrmEntrada() {

		// DEFINIR INFORMAÇÕES DA JANELA
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Cadastro de Veículo");
		setSize(400, 400);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		// PANIEL DO TITULO
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(0, 0, 400, 30);
		panelTitulo.setLayout(null);
		panelTitulo.setSize(630, 60);
		panelTitulo.setBackground(azul);

		lblIcone = new JLabel();
		lblIcone.setBounds(12, 5, 50, 50);
		lblIcone.setIcon(new ImageIcon(FrmEntrada.class.getResource("/br/senai/sp/imagens/edit32.png")));

		lblTitulo = new JLabel("Novo Veículo");
		lblTitulo.setForeground(branco);
		lblTitulo.setFont(titulo);
		lblTitulo.setBounds(62, 15, 300, 30);

		// PAINEL COMPONENTES
		JPanel panelComponentes = new JPanel();
		panelComponentes.setLayout(null);
		panelComponentes.setBounds(0, 50, 400, 300);

		// COMPONENTES DA TELA
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

		// BOTAO GRAVAR
		btGravar = new JButton("Gravar");
		btGravar.setBounds(10, 220, 120, 50);
		btGravar.setIcon(new ImageIcon(FrmEntrada.class.getResource("/br/senai/sp/imagens/save32.png")));

		// INSERINDO OBJETOS NOS PAINÉIS
		panelTitulo.add(lblTitulo);
		panelTitulo.add(lblIcone);
		panelComponentes.add(lblPlaca);
		panelComponentes.add(txtPlaca);
		panelComponentes.add(lblModelo);
		panelComponentes.add(txtModelo);
		panelComponentes.add(btGravar);

		// POSICIONAR COMPONENTES NA TELA
		getContentPane().add(panelTitulo);
		getContentPane().add(panelComponentes);

		// LISTENER PARA O BOTÃO
		btGravar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Cliente cliente = new Cliente();

				cliente.setPlaca(txtPlaca.getText());
				cliente.setModelo(txtModelo.getText());
				cliente.setData_entrada(Data.converterParaBanco(Data.horaAtual()));

				ClienteDAO dao = new ClienteDAO(cliente);

				dao.gravar();

				JOptionPane.showMessageDialog(null, "Veículo Gravado com Sucesso!", "Novo Veículo",
						JOptionPane.INFORMATION_MESSAGE);

				btGravar.setEnabled(false);
			}
		});
		setModal(true);
	}
}
