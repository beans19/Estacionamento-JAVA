package br.senai.sp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.senai.sp.model.Cliente;

public class ClienteDAO {

	private Cliente cliente;

	public ClienteDAO(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteDAO() {
	}

	// MÉTODO QUE INSERE ELEMENTOS NA TABELA MOVIMENTAÇÃO DO BANCO DE DADOS
	public void gravar() {

		String sql = "INSERT INTO tbl_movimentacao " + "(placa, modelo, data_entrada, data_saida, tempo, valor_pago)"
				+ "VALUES(?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement stm = Conexao.getConexao().prepareStatement(sql);
			stm.setString(1, cliente.getPlaca());
			stm.setString(2, cliente.getModelo());
			stm.setString(3, cliente.getData_entrada());
			stm.setString(4, cliente.getData_saida());
			stm.setInt(5, cliente.getTempo());
			stm.setDouble(6, cliente.getValor_pago());

			// Executar o comando no banco
			stm.execute();
			Conexao.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// MÉTODO QUE PEGA ELEMENTOS NA TABELA MOVIMENTAÇÃO DO BANCO DE DADOS PELO
	// CODIGO
	public Cliente getCliente(int codigo) {
		Cliente cliente = new Cliente();

		String sql = "SELECT * FROM tbl_movimentacao WHERE codigo = ?";

		try {
			PreparedStatement stm = Conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, codigo);

			ResultSet rs;

			rs = stm.executeQuery();

			rs.next();

			cliente.setCodigo(rs.getInt("codigo"));
			cliente.setPlaca(rs.getString("placa"));
			cliente.setModelo(rs.getString("modelo"));
			cliente.setData_entrada(rs.getString("data_entrada"));
			cliente.setData_saida(rs.getString("data_saida"));
			cliente.setTempo(rs.getInt("tempo"));
			cliente.setValor_pago(rs.getDouble("valor_pago"));

			Conexao.fecharConexao();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cliente;
	}

	// MÉTODO QUE LÊ TODOS OS ELEMENTOS NA TABELA MOVIMENTAÇÃO DO BANCO DE DADOS
	public ArrayList<Cliente> listarClientes() {

		String sql = "SELECT * FROM tbl_movimentacao";

		try {
			PreparedStatement stm = Conexao.getConexao().prepareStatement(sql);
			ResultSet rs;
			rs = stm.executeQuery();

			ArrayList<Cliente> clientes = new ArrayList<>();

			while (rs.next()) {
				Cliente cliente = new Cliente();

				cliente.setCodigo(rs.getInt("codigo"));
				cliente.setPlaca(rs.getString("placa"));
				cliente.setModelo(rs.getString("modelo"));
				cliente.setData_entrada(rs.getString("data_entrada"));
				cliente.setData_saida(rs.getString("data_saida"));
				cliente.setTempo(rs.getInt("tempo"));
				cliente.setValor_pago(rs.getDouble("valor_pago"));

				clientes.add(cliente);
			}

			Conexao.fecharConexao();
			return clientes;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	// MÉTODO QUE ATUALIZA OS ELEMENTOS NA TABELA MOVIMENTAÇÃO DO BANCO DE DADOS
	public void atualizar() {
		String sql = "UPDATE tbl_movimentacao " + "SET placa = ?, modelo = ?, " + "data_entrada = ?, data_saida = ?, "
				+ "tempo = ?, valor_pago = ? WHERE codigo = ?";

		try {
			PreparedStatement stm = Conexao.getConexao().prepareStatement(sql);
			stm.setString(1, cliente.getPlaca());
			stm.setString(2, cliente.getModelo());
			stm.setString(3, cliente.getData_entrada());
			stm.setString(4, cliente.getData_saida());
			stm.setInt(5, cliente.getTempo());
			stm.setDouble(6, cliente.getValor_pago());
			stm.setInt(7, cliente.getCodigo());

			stm.execute();
			Conexao.fecharConexao();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// MÉTODO QUE DELETA ELEMENTOS NA TABELA MOVIMENTAÇÃO DO BANCO DE DADOS
	public void excluir(int codigo) {
		String sql = "DELETE FROM tbl_movimentacao WHERE codigo = ?";

		try {
			PreparedStatement stm = Conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, codigo);
			stm.execute();

			Conexao.fecharConexao();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// MÉTODO QUE LÊ APENAS ELEMENTOS ESTACIONADOSS NA TABELA MOVIMENTAÇÃO DO BANCO
	// DE DADOS
	public ArrayList<Cliente> listarClientesEstacionados() {

		String sql = "SELECT * FROM tbl_movimentacao WHERE data_saida is NULL";

		try {
			PreparedStatement stm = Conexao.getConexao().prepareStatement(sql);
			ResultSet rs;
			rs = stm.executeQuery();

			ArrayList<Cliente> clientes = new ArrayList<>();

			while (rs.next()) {
				Cliente cliente = new Cliente();

				cliente.setCodigo(rs.getInt("codigo"));
				cliente.setPlaca(rs.getString("placa"));
				cliente.setModelo(rs.getString("modelo"));
				cliente.setData_entrada(rs.getString("data_entrada"));
				cliente.setData_saida(rs.getString("data_saida"));
				cliente.setTempo(rs.getInt("tempo"));
				cliente.setValor_pago(rs.getDouble("valor_pago"));

				clientes.add(cliente);
			}

			Conexao.fecharConexao();
			return clientes;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	// MÉTODO QUE LÊ O VALOR DA PRIMEIRA HORA NA TABELA VALOR DO BANCO DE DADOS
	public static double primeiraHora() {
		String sql = "SELECT valor_primeira_hora FROM tbl_valor WHERE data_fim is NULL";
		double primeiraHora = 0.0;
		PreparedStatement stm;
		try {
			ResultSet rs;
			stm = Conexao.getConexao().prepareStatement(sql);
			rs = stm.executeQuery();
			rs.next();
			primeiraHora = rs.getDouble("valor_primeira_hora");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Conexao.fecharConexao();
		return primeiraHora;
	}

	// MÉTODO QUE LÊ O VALOR DA PRIMEIRA HORA NA TABELA VALOR DO BANCO DE DADOS
	public static double demaisHoras() {
		String sql = "SELECT valor_demais_horas FROM tbl_valor WHERE data_fim is NULL";
		double demaisHoras = 0.0;
		PreparedStatement stm;
		try {
			ResultSet rs;
			stm = Conexao.getConexao().prepareStatement(sql);
			rs = stm.executeQuery();
			rs.next();
			demaisHoras = rs.getDouble("valor_demais_horas");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Conexao.fecharConexao();
		return demaisHoras;
	}

}
