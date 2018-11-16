package br.com.autopecas.modelo;

public class Produto
{

	private int id;	
	private double valor;
	private String descricao;
	
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public double getValor()
	{
		return valor;
	}
	
	public void setValor(double valor) 
	{
		this.valor = valor;
	}
	
	public String getDescricao()
	{
		return descricao;
	}
	
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}
	
	
}
