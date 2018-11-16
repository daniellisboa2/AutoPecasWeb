package br.com.autopecas.controle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.autopecas.dao.ProdutoDao;
import br.com.autopecas.modelo.Produto;
import br.com.autopecas.modelo.Usuario;

@RequestScoped
@ManagedBean

public class ProdutoBean 
{
	private Produto produto;
	private ProdutoDao produtoDao;
	private Usuario usuarioLogado;
	
	
	//Consultar produtos
	private String descricaoBusca;
	private List<Produto> produtos;
	private boolean mostrarTabela = false;	
	
	@PostConstruct
	public void init()
	{
		produto = new Produto();
		produtoDao = new ProdutoDao();
		
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
		usuarioLogado = (Usuario) session.getAttribute("user");
		
		if(usuarioLogado==null)
		{
			try {
				ctx.getExternalContext().redirect("/AutoPecasWeb/login.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	
	
	public void salvarProduto()
	{
		try 
		{
			if(produto.getDescricao().equalsIgnoreCase(""))		
			{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atençao!", "Preencha o campo descrição"));
			}
			
			else if(produto.getValor()==0)
			{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atençao!", "Preencha o campo valor"));
			}
			else
			{
				if(produtoDao.addProduto(produto)!=0)
				{
					 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Produto salvo com Sucesso"));					
				}
				
				else
				{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Erro ao Salvar Produto"));
				}
				
			}
			
		} 
		
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		
	}
	
	public void consultarProduto()
	{
		if(!descricaoBusca.equals(""))
		{
			
			try
			{
				produtos = produtoDao.buscaProdutoPorDescricao(descricaoBusca);
				if(produtos.size()>0) mostrarTabela = true;
			}
		
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		else
		{
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atençao!", "Preencha o campo de busca")); 
		}
	}


	public Produto getProduto() 
	{
		return produto;
	}


	public void setProduto(Produto produto)
	{
		this.produto = produto;
	}


	public String getDescricaoBusca()
	{
		return descricaoBusca;
	}


	public void setDescricaoBusca(String descricaoBusca) 
	{
		this.descricaoBusca = descricaoBusca;
	}


	public List<Produto> getProdutos() 
	{
		return produtos;
	}


	public void setProdutos(List<Produto> produtos) 
	{
		this.produtos = produtos;
	}


	public boolean isMostrarTabela() 
	{
		return mostrarTabela;
	}


	public void setMostrarTabela(boolean mostrarTabela)
	{
		this.mostrarTabela = mostrarTabela;
	}
	
	
	

}
