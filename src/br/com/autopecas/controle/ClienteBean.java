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

import br.com.autopecas.dao.ClienteDao;
import br.com.autopecas.modelo.Cliente;
import br.com.autopecas.modelo.Usuario;

@RequestScoped
@ManagedBean

public class ClienteBean 
{
	private Cliente cliente;
	private ClienteDao clienteDao;
	private Usuario usuarioLogado;
	
	//Consultar clientes
		private String nomeBusca;
		private List<Cliente> clientes;
		private boolean mostrarTabela = false;
	

	
	@PostConstruct
	public void init()
	{
		cliente = new Cliente();
		clienteDao = new ClienteDao();
		
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
	    
		public void salvarCliente()
		{
			try 
			{
				if(cliente.getNome().equalsIgnoreCase(""))		
				{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aten�ao!", "Preencha o campo Nome"));
				}
				
				else if(cliente.getCpf().equalsIgnoreCase(""))
				{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aten�ao!", "Preencha o campo CPF"));
				}
				
				else if(cliente.getTelefone().equalsIgnoreCase(""))
				{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aten�ao!", "Preencha o campo Telefone"));
				}
				
				else
				{
					if(clienteDao.addCliente(cliente)!=0)
					{
						 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Cliente salvo com Sucesso"));					
					}
					
					else
					{
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Erro ao Salvar Cliente"));
					}
					
				}
				
			} 
			
			catch (Exception e) 
			{
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
		}
		
		public void consultarCliente()
		{
			if(!nomeBusca.equals(""))
			{
				try 
				{
					clientes = clienteDao.buscaClientesPorNome(nomeBusca);
					if(clientes.size()>0) mostrarTabela = true;
				}
				catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			
			else
			{
				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aten�ao!", "Preencha o campo de busca")); 
			}
		}
		
		
		

		public Cliente getCliente() 
		{
			return cliente;
		}

		public void setCliente(Cliente cliente) 
		{
			this.cliente = cliente;
		}

		public String getNomeBusca() 
		{
			return nomeBusca;
		}

		public void setNomeBusca(String nomeBusca)
		{
			this.nomeBusca = nomeBusca;
		}

		public boolean isMostrarTabela() 
		{
			return mostrarTabela;
		}

		public void setMostrarTabela(boolean mostrarTabela) 
		{
			this.mostrarTabela = mostrarTabela;
		}

		public List<Cliente> getClientes() 
		{
			return clientes;
		}

		public void setClientes(List<Cliente> clientes) 
		{
			this.clientes = clientes;
		}		
		
		
}
