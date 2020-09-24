package controle;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import dao.ClienteDAO;
import modelo.Cliente;
import modelo.Usuario;
import visao.JanelaPrincipal;

public class Controle implements ActionListener, MouseListener {

	private JanelaPrincipal janela;
	private Cliente cliente;
	private Usuario usuario;
	private ClienteDAO clienteDao;
	private File imgFile = null;
	
	public Controle(JanelaPrincipal j) {
		// TODO Auto-generated constructor stub
		this.janela = j;
		
		this.janela.getButtonAutenticar().addActionListener(this);
		this.janela.getButtonLimparLogin().addActionListener(this);
		this.janela.getButtonGravar().addActionListener(this);
		this.janela.getButtonLimparCadastro().addActionListener(this);
		this.janela.getMenuItemSair().addActionListener(this);
		
		this.janela.getLblImgPerfil().addMouseListener(this);
		
		clienteDao = new ClienteDAO();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getComponent() == this.janela.getLblImgPerfil()) {
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Importar imagem de perfil");
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
			int resultado = fileChooser.showOpenDialog(this.janela);
			
			if(resultado == JFileChooser.APPROVE_OPTION) {
				imgFile = fileChooser.getSelectedFile();
								
				try {
					BufferedImage foto = ImageIO.read(imgFile);
			
					this.janela.getLblImgPerfil().setIcon(new ImageIcon(foto.getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
					
					this.janela.getLblImgPerfil().setText("");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand().equals("Autenticar")) {
			
			Usuario usuarioLogin = obterCamposLogin();
			if(usuarioLogin == null) {
				
			} else {
				boolean resultado = clienteDao.autenticarUsuario(usuarioLogin);
				
				if(resultado) {
					this.janela.getMenuItemCadastrar().setEnabled(true);
					this.janela.mensagemAutenticacao(true);
					limparCampos();
				} else {
					this.janela.mensagemAutenticacao(false);
					limparCampos();
				}
			}
			
		}
		
		if(e.getActionCommand().equals("Gravar")) {
			
			Cliente clienteCadastro = obterCamposCadastro();
			if(clienteCadastro == null) {
				this.janela.mensagemErroFormCadastro();
			} else {
				boolean resultado = clienteDao.cadastrarCliente(clienteCadastro);
				
				if(resultado) {
					this.janela.mensagemCadastro(true);
					limparCampos();
					this.janela.getLblImgPerfil().setText("Escolha a foto de perfil");
				} else {
					this.janela.mensagemCadastro(false);
				}
			}
			
		}
		
		if(e.getActionCommand().equals("Limpar")) {
			
			limparCampos();
			
		}
		
		if(e.getActionCommand().equals("Sair")) {
			System.exit(0);
		}
		
	}
	
	public Usuario obterCamposLogin() {
		String nomeUsuario = this.janela.getFieldUsuario().getText();
		String senha = this.janela.getFieldSenha().getText();
		
		if(nomeUsuario == null || senha == null) {
			return null;
		} else {
			usuario = new Usuario(nomeUsuario, senha);
			
			return usuario;
		}
	}
	
	public Cliente obterCamposCadastro() {
		String nome = this.janela.getFieldNome().getText();
		String cpf = this.janela.getFieldCpf().getText();
		String endereco = this.janela.getFieldEndereco().getText();
		String sexo = obterRadioButton();
		String profissao = this.janela.getComboBoxProfissao().getSelectedItem().toString();
		
		FileInputStream fotoPerfil = null;
		try {
			if(imgFile == null) {
				return null;
			} else {
				fotoPerfil = new FileInputStream(imgFile); // imgFile obtido no FileChooser
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		this.janela.getComboBoxProfissao().setSelectedIndex(0);		
		this.janela.getLblImgPerfil().setIcon(null);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
