package controle;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

import dao.ClienteDAO;
import modelo.Cliente;
import visao.JanelaPrincipal;

public class Controle implements ActionListener {

	private JanelaPrincipal janela;
	private Cliente cliente;
	private ClienteDAO clienteDao;
	
	public Controle(JanelaPrincipal j) {
		// TODO Auto-generated constructor stub
		this.janela = j;
		
		this.janela.getButtonAutenticar().addActionListener(this);
		this.janela.getButtonLimparLogin().addActionListener(this);
		this.janela.getButtonGravar().addActionListener(this);
		this.janela.getButtonLimparCadastro().addActionListener(this);
		
		clienteDao = new ClienteDAO();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand().equals("Autenticar")) {
			
			Cliente clienteLogin = obterCamposLogin();
			if(clienteLogin == null) {
				
			} else {
				clienteDao.autenticarCliente(clienteLogin);
			}
			
		}
		
		if(e.getActionCommand().equals("Cadastrar")) {
			
			Cliente clienteCadastro = obterCamposCadastro();
			if(clienteCadastro == null) {
				
			} else {
				clienteDao.autenticarCliente(clienteCadastro);
			}
			
		}
		
		if(e.getActionCommand().equals("Limpar")) {
			
		}
		
	}
	
	public Cliente obterCamposLogin() {
		String usuario = this.janela.getFieldUsuario().getText();
		// String senha = this.janela.getFieldSenha().getText();
		
		if(usuario == null) {
			return null;
		} else {
			cliente = new Cliente(usuario, null, null, null, null, null);
			
			return cliente;
		}
	}
	
	public Cliente obterCamposCadastro() {
		String nome = this.janela.getFieldNome().getText();
		String cpf = this.janela.getFieldCpf().getText();
		String endereco = this.janela.getFieldEndereco().getText();
		String sexo = obterRadioButton();
		String profissao = this.janela.getComboBoxProfissao().getSelectedItem().toString();
		FileInputStream fotoPerfil = (FileInputStream) this.janela.getLblImgPerfil().getIcon();
		
		if(nome == null || cpf == null || endereco == null || sexo == null || profissao == null || fotoPerfil == null) {
			return null;
		} else {
			cliente = new Cliente(nome, cpf, endereco, sexo, profissao, fotoPerfil);
			
			return cliente;
		}
	}
	
	public String obterRadioButton() {
		if(this.janela.getRadioButtonFem().isSelected()) {
			return this.janela.getRadioButtonFem().getText();
		} else {
			return this.janela.getRadioButtonMasc().getText();
		}
	}
	
	public void limparCampos() {
		this.janela.getFieldUsuario().setText("");
		this.janela.getFieldSenha().setText("");
		this.janela.getFieldNome().setText("");
		this.janela.getFieldCpf().setText("");
		this.janela.getFieldEndereco().setText("");
		this.janela.getButtonGroup().clearSelection();
		this.janela.getComboBoxProfissao().setSelectedItem(""); // Rever		
		this.janela.getLblImgPerfil().setIcon(null);
	}
	
}
