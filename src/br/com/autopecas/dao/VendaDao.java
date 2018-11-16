package br.com.autopecas.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import br.com.autopecas.conexao.Conexao;
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

	

}
