package br.com.autopecas.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.autopecas.conexao.Conexao;
import br.com.autopecas.modelo.Produto;

public class ProdutoDao
{
	public int addProduto (Produto produto) throws SQLException
	{
		Conexao conexao = new Conexao();
		Connection conn;
		int resultado = 0;
		
		String sql = "insert into produtos(descricao,valor)"
		+ "values('"+produto.getDescricao()+"','"+produto.getValor()+"')";
		
		conn = conexao.conectar();//retorna a conexao com o banco aberta
		if(conn != null)
		{
			Statement st = conn.createStatement();
			resultado = st.executeUpdate(sql);
			conexao.desconectar();
		}
		
		return resultado;
		
	}

	
	public List<Produto> buscaProdutoPorDescricao(String descricao) throws SQLException
	{
		Conexao c = new Conexao();
		Connection cc = c.conectar();
		List<Produto> produtos = new ArrayList<Produto>();
		
		String consulta = "Select * from produtos where descricao like '%"+descricao+"%'";
		Statement stm = (Statement) cc.createStatement();
		ResultSet result = stm.executeQuery(consulta);
		
		while(result.next())
		{
         
			Produto p = new Produto(); // cria um objeto produto
			
			p.setId(result.getInt("id_produto"));
			p.setDescricao(result.getString("descricao"));
			p.setValor(result.getDouble("valor")); // 
						
			produtos.add(p);
		}
		
		return produtos;
	}
	
	public List<Produto> buscarTodosProdutos () throws SQLException
	{
		Conexao c = new Conexao();
		Connection cc = c.conectar();
		List<Produto> produtos = new ArrayList<Produto>();
		
		String consulta = "Select * from produtos ";
		Statement stm = (Statement) cc.createStatement();
		ResultSet result = stm.executeQuery(consulta);
		
		while(result.next())
		{
			Produto p = new Produto();
			
			p.setId(result.getInt("id_produto"));
			p.setDescricao(result.getString("descricao"));
			p.setValor(result.getDouble("valor")); // 
						
			produtos.add(p);
        
		
	    }
		return produtos;

	}
	
	
	public Produto buscaProdutoPorId(int id) throws SQLException
	{
		Conexao c = new Conexao();
		Connection cc = c.conectar();
				
		String consulta = "Select * from produtos where id_produto ="+id;
		Statement stm = (Statement) cc.createStatement();
		ResultSet result = stm.executeQuery(consulta);
		
		while(result.next())
		{
         
			Produto p = new Produto(); // cria um objeto produto
			
			p.setId(result.getInt("id_produto"));
			p.setDescricao(result.getString("descricao"));
			p.setValor(result.getDouble("valor")); // 
			
			return p;
			
		}
		
		return null;
	}
	
	public int editarProduto(Produto produto) throws SQLException{
		Conexao c = new Conexao();
		Connection cc = c.conectar();
		
		String sql = "update produtos set descricao = '"+produto.getDescricao()+"',"
				+ "valor =" +produto.getValor()+		
				 "where id_produto =" +produto.getId();
		
		Statement stm = (Statement) cc.createStatement();
		int result = stm.executeUpdate(sql);
		
		c.desconectar();
		return result;
	}
	
	public int deletarProduto(Produto produto) throws SQLException {

		Conexao c = new Conexao();
		Connection cc = c.conectar();
	

		String sql = "delete  from produtos where id_produto = "+produto.getId();
				

		
			Statement stm = (Statement) cc.createStatement();
			int result = stm.executeUpdate(sql);
			
			
		c.desconectar();
		return result;
		
	}
	
}
