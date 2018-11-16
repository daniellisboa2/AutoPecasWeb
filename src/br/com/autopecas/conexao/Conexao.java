package br.com.autopecas.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao 
{
	
    private Connection con = null;
	
	
	public Connection conectar()
	{
		String endereco = "jdbc:postgresql://localhost:5432/AutoPecas";
		String usuario = "postgres";
		String senha = "Gitmaster";
		
		// passando o nome do drive do PostgreSQL 
		
		try
		{
			Class.forName("org.postgresql.Driver");
			
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		// Obtendo a conexao com o banco de dados
		
		try
		{
			con = DriverManager.getConnection(endereco,usuario,senha);
			System.out.println("conexao efetuada");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		//retorna um erro caso nao encontre o driver, ou alguma informaçao sobre o mesmo
		
		return con;
	}
	
	public void desconectar()
	{
		try
		{
			// fecha a conexao
			con.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	

}
