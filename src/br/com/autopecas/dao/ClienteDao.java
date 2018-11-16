package br.com.autopecas.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.autopecas.conexao.Conexao;
import br.com.autopecas.modelo.Cliente;

public class ClienteDao 
{
	public int addCliente (Cliente cliente) throws SQLException
	{
		Conexao conexao = new Conexao();
		Connection conn;
		int resultado = 0;
		
		String sql = "insert into clientes(nome,cpf,telefone)"
		+ "values('"+cliente.getNome()+"','"+cliente.getCpf()+"','"+cliente.getTelefone()+"')";
		
		conn = conexao.conectar();//retorna a conexao com o banco aberta
		if(conn != null)
		{
			Statement st = conn.createStatement();
			resultado = st.executeUpdate(sql);
			conexao.desconectar();
		}
		
		return resultado;
		
	}
	
	public List<Cliente> buscaClientesPorNome(String nome) throws SQLException
	{
		
		Conexao c = new Conexao();
		Connection cc = c.conectar();
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		String consulta = "Select * from clientes where nome like '%"+nome+"%'";
		Statement stm = (Statement) cc.createStatement();
		ResultSet result = stm.executeQuery(consulta);
		
		while(result.next())
		{
			
            Cliente c1 = new Cliente(); // cria um objeto cliente
			
			c1.setId(result.getInt("id_cliente"));
			c1.setNome(result.getString("nome"));
			c1.setCpf(result.getString("cpf")); // 
			c1.setTelefone(result.getString("telefone"));
			
			clientes.add(c1);
		}
		
		return clientes;
	}
	
	public List<Cliente> buscaTodosClientes() throws SQLException
	{
		
		Conexao c = new Conexao();
		Connection cc = c.conectar();
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		String consulta = "Select * from clientes ";
		Statement stm = (Statement) cc.createStatement();
		ResultSet result = stm.executeQuery(consulta);
		
		while(result.next())
		{			
       
			Cliente c1 = new Cliente(); // cria um objeto cliente
			
			c1.setId(result.getInt("id_cliente"));
			c1.setNome(result.getString("nome"));
			c1.setCpf(result.getString("cpf")); // 
			c1.setTelefone(result.getString("telefone"));
			
			clientes.add(c1);
		}
		
		return clientes;
	}
	
	public Cliente buscaClientesPorId(int id) throws SQLException
	{
		
		Conexao c = new Conexao();
		Connection cc = c.conectar();
		
		
		String consulta = "Select * from clientes where id_cliente ="+id;
		Statement stm = (Statement) cc.createStatement();
		ResultSet result = stm.executeQuery(consulta);
		
		while(result.next())
		{
			
            Cliente c1 = new Cliente(); // cria um objeto cliente
			
			c1.setId(result.getInt("id_cliente"));
			c1.setNome(result.getString("nome"));
			c1.setCpf(result.getString("cpf")); // 
			c1.setTelefone(result.getString("telefone"));
			
			return c1;
		}
		
		return null;
	}
	


}
