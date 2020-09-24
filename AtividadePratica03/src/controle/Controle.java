package controle;

import java.awt.Dimension;
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
import visao.JanelaPrincipal;

public class Controle implements ActionListener, MouseListener {

	private JanelaPrincipal janela;
	private Cliente cliente;
	private ClienteDAO clienteDao;
	private File imgFile;
	
	public Controle(JanelaPrincipal j) {
		// TODO Auto-generated constructor stub
		this.janela = j;
		
		this.janela.getButtonAutenticar().addActionListener(this);
		this.janela.getButtonLimparLogin().addActionListener(this);
		this.janela.getButtonGravar().addActionListener(this);
		this.janela.getButtonLimparCadastro().addActionListener(this);
		
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
					// foto.getScaledInstance(this.janela.getLblImgPerfil().getWidth(), this.janela.getLblImgPerfil().getHeight(), BufferedImage.TYPE_INT_RGB);
					// ImageIcon iconeFoto = new ImageIcon(foto);
					this.janela.getLblImgPerfil().setIcon(new ImageIcon(foto.getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
					//this.janela.getLblImgPerfil().revalidate();
					//this.janela.getLblImgPerfil().repaint();
					
					//Dimension tamanhoFoto = new Dimension(this.janela.getLblImgPerfil().getWidth(), this.janela.getLblImgPerfil().getHeight());
					//this.janela.getLblImgPerfil().setPreferredSize(tamanhoFoto);
					
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
			
			Cliente clienteLogin = obterCamposLogin();
			if(clienteLogin == null) {
				
			} else {
				clienteDao.autenticarCliente(clienteLogin);
			}
			
		}
		
		if(e.getActionCommand().equals("Gravar")) {
			
			Cliente clienteCadastro = obterCamposCadastro();
			if(clienteCadastro == null) {
				System.out.println("Deu ruim");
			} else {
				boolean resultado = clienteDao.cadastrarCliente(clienteCadastro);
				
				if(resultado) {
					System.out.println("Tudo certo");
				}
			}
			
		}
		
		if(e.getActionCommand().equals("Limpar")) {
			
			limparCampos();
			
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
		
		FileInputStream fotoPerfil = null;
		try {
			fotoPerfil = new FileInputStream(imgFile); // imgFile obtido no FileChooser
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
