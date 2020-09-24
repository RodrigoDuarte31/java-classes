package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import modelo.Cliente;

public class ClienteDAO {
	
	private Connection con = null;
	
	public ClienteDAO() {
		// TODO Auto-generated constructor stub
	}
	
	// Consulta cliente
	public boolean autenticarCliente(Cliente c) {
		return false;
	}
	
	// Cadastra cliente
	public boolean cadastrarCliente(Cliente c) {
		ConexaoMySQL.abrirConexao();
		con = ConexaoMySQL.getCon();
		
		if(con != null) {
			
			String sql = "INSERT INTO cliente VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement prepStmt = null;
			
			try {
				prepStmt = con.prepareStatement(sql);
				
				prepStmt.setString(1, c.getCpf());
				prepStmt.setString(2, c.getNome());
				prepStmt.setString(3, c.getSexo());
				prepStmt.setString(4, c.getProfissao());
				prepStmt.setString(5, c.getEndereco());
				prepStmt.setBlob(6, c.getFotoPerfil());
				
				int res = prepStmt.executeUpdate();
				
				if(res == 1) {
					ConexaoMySQL.fecharConexao();
					return true;
				} else {
					ConexaoMySQL.fecharConexao();
					return false;
				}
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
				return false;
			}
		}
		
		return false;
	}

}
