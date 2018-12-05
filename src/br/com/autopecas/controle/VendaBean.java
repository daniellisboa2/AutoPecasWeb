package br.com.autopecas.controle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.autopecas.dao.ClienteDao;
import br.com.autopecas.dao.ProdutoDao;
import br.com.autopecas.dao.VendaDao;
import br.com.autopecas.modelo.Cliente;
import br.com.autopecas.modelo.Produto;
import br.com.autopecas.modelo.Usuario;
import br.com.autopecas.modelo.Venda;

@ViewScoped
@ManagedBean

public class VendaBean
{
		
	private ClienteDao clienteDao;
	private ProdutoDao produtoDao;
	private VendaDao vendaDao;
	
	private Venda venda;
	
	private List<Produto> produtos;
	private List<Cliente> clientes;
	
	private int idprodutoSelecionado = 0;
	private int idclienteSelecionado = 0;
	
	private int qtdadeProduto = 0;
	
	private List<Venda> vendas;
	private boolean mostrarTabela = false;
	
	private Usuario usuarioLogado;
	
	@PostConstruct
	public void init()
    {
	
		clienteDao = new ClienteDao();
		produtoDao = new ProdutoDao();
		vendaDao = new VendaDao();		
		
		venda = new Venda();
		
		produtos = new ArrayList<Produto>();
		clientes = new ArrayList<Cliente>();
		
		
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
		usuarioLogado = (Usuario) session.getAttribute("user");
		
		if(usuarioLogado==null)
		{
			try 
			{
				ctx.getExternalContext().redirect("/AutoPecasWeb/login.xhtml");
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try 
		{
			produtos = produtoDao.buscarTodosProdutos();
			clientes = clienteDao.buscaTodosClientes();
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
    }
		
		public void salvarVenda()
		{
			try 
			{				
				if (idclienteSelecionado == 0)
				{
					FacesContext.getCurrentInstance().addMessage
					(null,	new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Atenção!", "Selecione o cliente"));

				} 
				
				else if (idprodutoSelecionado == 0) {
					FacesContext.getCurrentInstance().addMessage
					(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Atenção!", "Selecione o produto"));
				}
				
				else if (qtdadeProduto == 0) {
					FacesContext.getCurrentInstance().addMessage
					(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Atenção!", "Selecione a qtdade de produto"));
				}
				
				else if (venda.getDataVenda() == null) {
					FacesContext.getCurrentInstance().addMessage
					(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Atenção!", "Selecione a data"));
				}
				
				
				else 
				{

					venda.setCliente(clienteDao.buscaClientesPorId(idclienteSelecionado));
					venda.setProduto(produtoDao.buscaProdutoPorId(idprodutoSelecionado));
					venda.calcularVenda(qtdadeProduto, venda.getProduto().getValor());

					if (!vendaDao.existeVendaCadastrada(venda)) 
					{
						if (vendaDao.addVenda(venda)!= 0) {
							FacesContext.getCurrentInstance().addMessage
							(null,	new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Sucesso", "Venda salva com sucesso!"));

						} 
						else 
						{
							FacesContext.getCurrentInstance().addMessage
							(null,	new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro", "Erro ao salvar venda"));

						}
						
					}
					
					else
					{
						FacesContext.getCurrentInstance().addMessage(
						null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Atenção!", "Já existe um registro de venda para esse cliente e esse produto"));
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		public void ConsultarVenda() {
			try {
				vendas = vendaDao.buscarVendaPorCliente(idclienteSelecionado);
				if(vendas.size()>0) 
					mostrarTabela = true;
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}

	}
		
		public void deletarVenda(Venda venda) {
			try {
				int resultado = vendaDao.deletarVenda(venda);
				if(resultado !=0) {
					vendas = vendaDao.buscarVendaPorCliente(idclienteSelecionado);
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}

	}
		

		public List<Produto> getProdutos()
		{
			return produtos;
		}

		public void setProdutos(List<Produto> produtos) 
		{
			this.produtos = produtos;
		}

		public List<Cliente> getClientes() 
		{
			return clientes;
		}

		public void setClientes(List<Cliente> clientes) 
		{
			this.clientes = clientes;
		}

		public int getIdprodutoSelecionado() 
		{
			return idprodutoSelecionado;
		}

		public void setIdprodutoSelecionado(int idprodutoSelecionado) 
		{
			this.idprodutoSelecionado = idprodutoSelecionado;
		}

		public int getIdclienteSelecionado() 
		{
			return idclienteSelecionado;
		}

		public void setIdclienteSelecionado(int idclienteSelecionado) 
		{
			this.idclienteSelecionado = idclienteSelecionado;
		}

		public Venda getVenda() 
		{
			return venda;
		}

		public void setVenda(Venda venda) 
		{
			this.venda = venda;
		}

		public int getQtdadeProduto()
		{
			return qtdadeProduto;
		}

		public void setQtdadeProduto(int qtdadeProduto) 
		{
			this.qtdadeProduto = qtdadeProduto;
		}

		public boolean isMostrarTabela() {
			return mostrarTabela;
		}

		public void setMostrarTabela(boolean mostrarTabela) {
			this.mostrarTabela = mostrarTabela;
		}

		public List<Venda> getVendas() {
			return vendas;
		}

		public void setVendas(List<Venda> vendas) {
			this.vendas = vendas;
		}
		
		
		
	
		
}

