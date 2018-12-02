package br.com.autopecas.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.autopecas.conexao.Conexao;
import br.com.autopecas.modelo.Cliente;
import br.com.autopecas.modelo.Produto;
import br.com.autopecas.modelo.Venda;

public class VendaDao 
{
	public int addVenda(Venda venda) throws SQLException
	{
		Conexao conexao = new Conexao();
		Connection conn;
		int resultado = 0;
		
		String sql = "insert into vendas(id_cliente,id_produto,data_venda,vl_venda)"
		+ "values('"+venda.getCliente().getId()+"','"+venda.getProduto().getId()+"','"
		+ venda.getDataVenda()+"','"+venda.getValorVenda()+"')";
		
		conn = conexao.conectar();//retorna a conexao com o banco aberta
		if(conn != null)
		{
			Statement st = conn.createStatement();
			resultado = st.executeUpdate(sql);
			conexao.desconectar();
		}
		
		return resultado;
	
	}
	
	public boolean existeVendaCadastrada(Venda venda) throws SQLException {

		Conexao c = new Conexao();
		Connection cc = c.conectar();

		String consulta = "Select * from vendas where id_cliente ="
				+ venda.getCliente().getId()+ " and id_produto = "
				+ venda.getProduto().getId();
		Statement stm = (Statement) cc.createStatement();
		ResultSet result = stm.executeQuery(consulta);

		while (result.next()) {

			return true;
		}

		return false;
	}
	
	public List<Venda> buscarVendaPorCliente(int id) throws SQLException {
		Conexao c = new Conexao();
		Connection cc = c.conectar();
		List<Venda> vendas = new ArrayList<Venda>();
		
		String consulta = "Select * from vendas where id_cliente="+id;
		Statement stm = (Statement) cc.createStatement();
		ResultSet result = stm.executeQuery(consulta);
		
		while(result.next()) {
			Venda a = new Venda();
			Cliente c1 = new Cliente();
			Produto p = new Produto();
			
			
			c1.setId(result.getInt("id_cliente"));
			p.setId(result.getInt("id_produto"));
			a.setCliente(c1);			
			a.setProduto(p);
			a.setId(result.getInt("id_vendas"));
			a.setDataVenda(result.getDate("data_venda"));
			a.setValorVenda(result.getDouble("vl_venda"));
			
			vendas.add(a);
		}
		return vendas;
	}

	public int deletarVenda(Venda venda) throws SQLException {
		
		Conexao c = new Conexao();
		Connection cc = c.conectar();
		
		String sql = "delete from vendas where id_vendas =" +venda.getId();
				
		
		Statement stm = (Statement) cc.createStatement();
		int result = stm.executeUpdate(sql);
		
		c.desconectar();
		return result;
	
}

	

}
