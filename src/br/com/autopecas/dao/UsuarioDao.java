package br.com.autopecas.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.autopecas.conexao.Conexao;
import br.com.autopecas.modelo.Usuario;



public class UsuarioDao 
{
	
	public Usuario buscarUsuario (String login, String senha ) throws SQLException  
	{
		Usuario usuario = null;
		Conexao conexao = new Conexao();
		Connection conn;
		
		
		String sql = "select * from Usuarios where login like '"+login+"' and senha like '"+senha+"'";
	
			conn = conexao.conectar();//retorna a conexao com o banco aberta
			if(conn != null)
			{
				Statement st = conn.createStatement();
				ResultSet result = st.executeQuery(sql);
				
				while(result.next())
				{
					usuario = new Usuario();
					usuario.setId(result.getInt("id_usuario"));
					usuario.setLogin(result.getString("login"));
					usuario.setNome(result.getString("nome"));
					usuario.setSenha(result.getString("senha"));
				}
												
				conexao.desconectar();
			}
			
			return usuario;
					
						
	}
	
	public int AlterarSenha(String novaSenha,Usuario u ) throws SQLException  
	{
		
		Conexao conexao = new Conexao();
		Connection conn;
		int resultado = 0;
		
		
		String sql = "update usuarios set senha="+novaSenha+" where id_usuario="+u.getId()+"";
	
			conn = conexao.conectar();//retorna a concexao com o banco aberta
			if(conn != null)
			{
				Statement st = conn.createStatement();
				resultado = st.executeUpdate(sql);
				
														
				conexao.desconectar();
			}
			
			return resultado;
					
						
	}
	
	

}
