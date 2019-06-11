package br.senai.sp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static Connection conexao;

	// M�TODO QUE REALIZA A CONEX�O AO BANCO DE DADOS
	public static Connection getConexao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dbUrl = "jdbc:mysql://127.0.0.1/db_estacionamento?useTimezone=true&serverTimezone=UTC&useSSL=false";
			conexao = DriverManager.getConnection(dbUrl, "root", "bcd127");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("A conex�o falhou!!");
			e.printStackTrace();
		}
		return conexao;
	}

	// M�TODO QUE ENCERRA A CONEX�O AO BANCO DE DADOS
	public static void fecharConexao() {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}