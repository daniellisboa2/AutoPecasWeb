package br.com.autopecas.controle;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.autopecas.dao.UsuarioDao;
import br.com.autopecas.modelo.Usuario;



@SessionScoped // isso e pra os dados do usuario ficar em todas as paginas a n ser q seja encerrada a sessao
@ManagedBean
public class UsuarioBean 
{
	private UsuarioDao usuarioDao;
	private Usuario usuario = null;
	private String login;
	private String senha;
	private String novaSenha;
	private String confirmarSenha;

	
	
	@PostConstruct
	public void init()
	{
		usuarioDao = new UsuarioDao();
	
	}
	
		public String buscarUsuario()
		{
			
			try
			{
			
				usuario = usuarioDao.buscarUsuario(login, senha);
			
				if(usuario != null)
				{
					FacesContext ctx = FacesContext.getCurrentInstance();
					HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
					session.setAttribute("user", usuario);
				
					return "/index";				
				}
			
				else
				{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Dados incorretos"));
				}
			
			}
			
			catch (SQLException e) 
			{
				
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Erro ao consultar usuario"));
		    }
			
			return null;
	   }
		
	
	public String logout() 
	{
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
		session.removeAttribute("user");
		session.invalidate();
		
		return "/login";		
	}
	
	
	public void trocarSenha() 
	{
		try 
		{
			 if (this.getNovaSenha().equalsIgnoreCase("")) 
			 {
				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atençao!", "Preencha o campo Nova Senha")); 
				 
			 }
			 
			 else if (this.getConfirmarSenha().equalsIgnoreCase("")) 
			 {
				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atençao!", "Preencha o campo Confirmar Senha"));
								 
			 }
			 
			 else 
			 {
				 if (novaSenha.equals(confirmarSenha))
				 {
					 usuarioDao.AlterarSenha(novaSenha,usuario);
					 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "senha modificada com Sucesso"));
					 
				 }
				 
				 else
				 {
					 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "senhas incorretas"));
					 
				 }
			 }
			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public String getLogin() 
	{
		return login;
	}

	public void setLogin(String login) 
	{
		this.login = login;
	}

	public String getSenha()
	{
		return senha;
	}

	public void setSenha(String senha) 
	{
		this.senha = senha;
	}

	public String getNovaSenha() 
	{
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) 
	{
		this.novaSenha = novaSenha;
	}

	public String getConfirmarSenha()
	{
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha)
	{
		this.confirmarSenha = confirmarSenha;
	}

}
