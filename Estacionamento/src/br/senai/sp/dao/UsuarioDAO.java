package br.senai.sp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.senai.sp.model.Usuario;

public class UsuarioDAO {

	public UsuarioDAO() {
		// TODO Auto-generated constructor stub
	}

	public Usuario getUsuario(String usuario, String senha) {
		Usuario user = new Usuario();

		String sql = "SELECT * FROM tbl_login WHERE usuario = ? AND senha = ?";

		try {
			PreparedStatement stm = Conexao.getConexao().prepareStatement(sql);
			stm.setString(1, usuario);
			stm.setString(2, senha);

			ResultSet rs;

			rs = stm.executeQuery();

			rs.next();

			user.setId(rs.getInt("id"));
			user.setUsuario(rs.getString("usuario"));
			user.setSenha(rs.getString("senha"));

			Conexao.fecharConexao();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return user;
	}
}
