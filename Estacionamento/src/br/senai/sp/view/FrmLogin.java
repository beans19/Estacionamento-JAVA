package br.senai.sp.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.senai.sp.dao.UsuarioDAO;
import br.senai.sp.model.Usuario;

public class FrmLogin extends JFrame {

	public JLabel lblTitulo;
	public JLabel lblUsuario;
	public JLabel lblSenha;
	public JTextField txtUsuario;
	public JPasswordField txtSenha;
	public JButton btEntrar;

	// CORES & FONTES
	private Color azul = new Color(0, 0, 255);
	private Color branco = new Color(255, 255, 255);
	private Font titulo = new Font("Arial", 1, 30);
	private Font texto = new Font("Arial", 1, 20);

	public FrmLogin() {

		// DEFINIR INFORMAÇÕES DA JANELA
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Cadastro de Veículo");
		setSize(400, 400);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		// PAINEL DO TITULO
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(0, 0, 400, 30);
		panelTitulo.setLayout(null);
		panelTitulo.setSize(630, 60);
		panelTitulo.setBackground(azul);

		lblTitulo = new JLabel("Philip's Parking Lot");
		lblTitulo.setForeground(branco);
		lblTitulo.setFont(titulo);
		lblTitulo.setBounds(55, 15, 300, 30);

		// PAINEL COMPONENTES
		JPanel panelComponentes = new JPanel();
		panelComponentes.setLayout(null);
		panelComponentes.setBounds(0, 50, 400, 300);

		// COMPONENTES DA TELA
		lblUsuario = new JLabel("Usuário");
		lblUsuario.setBounds(10, 0, 100, 100);
		lblUsuario.setFont(texto);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(10, 70, 200, 30);

		lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 90, 100, 100);
		lblSenha.setFont(texto);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(10, 160, 200, 30);

		// BOTAO ENTRAR
		btEntrar = new JButton("Entrar");
		btEntrar.setBounds(10, 220, 120, 50);

		// INSERINDO OBJETOS NOS PAINÉIS
		panelTitulo.add(lblTitulo);
		panelComponentes.add(lblUsuario);
		panelComponentes.add(lblSenha);
		panelComponentes.add(txtUsuario);
		panelComponentes.add(txtSenha);
		panelComponentes.add(btEntrar);

		// POSICIONAR COMPONENTES NA TELA
		getContentPane().add(panelTitulo);
		getContentPane().add(panelComponentes);
		
		// LISTENER PARA O BOTÃO
		btEntrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Usuario user = new Usuario();
				
				// USUÁRIO E SENHA PARA O LOGIN:
				// usuário: admin
				// senha: admin
				
				user.setUsuario(txtUsuario.getText());
				user.setSenha(String.valueOf(txtSenha.getPassword()));
				
				UsuarioDAO dao = new UsuarioDAO();
				
				user = dao.getUsuario(user.getUsuario(), user.getSenha());
				
				if(user == null) {
					JOptionPane.showMessageDialog(null, "Por favor, verifique seu usuário e senha!", "Campos Inválidos",
							JOptionPane.ERROR_MESSAGE);
				}else{
					FrmPrincipal principal = new FrmPrincipal();
					dispose();
				}
			}
		});
		
		setVisible(true);
	}
}
