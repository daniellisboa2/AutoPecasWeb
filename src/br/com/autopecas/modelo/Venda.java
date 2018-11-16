package br.com.autopecas.modelo;

import java.util.Date;

public class Venda 
{
	
	private Cliente cliente;
	private Produto produto;
	private Date dataVenda;
	private double valorVenda;
	
    public void calcularVenda(int qtdade, double vlProduto)
    {
    	this.valorVenda = qtdade * vlProduto;
    }
	
	public Cliente getCliente() 
	{
		return cliente;
	}
	
	public void setCliente(Cliente cliente) 
	{
		this.cliente = cliente;
	}
	
	public Produto getProduto() 
	{
		return produto;
	}
	
	public void setProduto(Produto produto)
	{
		this.produto = produto;
	}
	
	public Date getDataVenda() 
	{
		return dataVenda;
	}
	
	public void setDataVenda(Date dataVenda)
	{
		this.dataVenda = dataVenda;
	}
	
	public double getValorVenda() 
	{
		return valorVenda;
	}
	
	public void setValorVenda(double valorVenda) 
	{
		this.valorVenda = valorVenda;
	}	
	

}
