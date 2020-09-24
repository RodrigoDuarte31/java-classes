package visao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;

import java.awt.FlowLayout;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class JanelaPrincipal extends JFrame {

	private JPanel contentPane;
	private static JanelaPrincipal frame; 
	private JPanel painelPrincipal;
	private JPanel painelLogin;
	private JPanel painelCadastro;
	private JTextField fieldUsuario;
	private JTextField fieldSenha;
	private JTextField fieldNome;
	private JTextField fieldCpf;
	private JTextField fieldEndereco;
	private JButton buttonAutenticar;
	private JButton buttonLimparLogin;
	private JLabel lblImgPerfil;
	private JRadioButton radioButtonMasc;
	private JRadioButton radioButtonFem;
	private JButton buttonGravar;
	private JButton buttonLimparCadastro;
	private ButtonGroup buttonGroup;
	private JComboBox<String> comboBoxProfissao;
	private JMenuItem menuItemCadastrar;
	private JMenuItem menuItemAutenticar;
	private JMenuItem menuItemSair;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new JanelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JanelaPrincipal() {
		setTitle("Janela Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		
		menuItemAutenticar = new JMenuItem("Autenticar");
		menuItemAutenticar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CardLayout cl = (CardLayout) painelPrincipal.getLayout();
				cl.show(painelPrincipal, "telaLogin");
				
			}
		});
		menu.add(menuItemAutenticar);
		
		menuItemCadastrar = new JMenuItem("Cadastrar");
		menuItemCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CardLayout cl = (CardLayout) painelPrincipal.getLayout();
				cl.show(painelPrincipal, "telaCadastro");
				
			}
		});
		menu.add(menuItemCadastrar);
		menuItemCadastrar.setEnabled(false); // Desabilita o botao do painel cadastro
		
		menuItemSair = new JMenuItem("Sair");
		menu.add(menuItemSair);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		// Painel Principal
		painelPrincipal = new JPanel();
		painelPrincipal.setBackground(Color.WHITE);
		contentPane.add(painelPrincipal, "telaPrincipal");
		painelPrincipal.setLayout(new CardLayout(0, 0));
		
		// Painel da tela de login
		painelLogin = new JPanel();
		painelLogin.setBackground(SystemColor.control);
		painelPrincipal.add(painelLogin, "telaLogin");
		painelLogin.setLayout(new BorderLayout(0, 0));
		
		JPanel painelIconeLogin = new JPanel();
		painelLogin.add(painelIconeLogin, BorderLayout.WEST);
		
		JLabel lblIconeLogin = new JLabel("");
		lblIconeLogin.setAlignmentX(0.5f);
		lblIconeLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconeLogin.setBounds(0, 0, 120, 120);
		try {
			lblIconeLogin.setIcon(new ImageIcon(ImageIO.read(JanelaPrincipal.class.getResource("/figuras/login.png")).getScaledInstance(lblIconeLogin.getWidth(), lblIconeLogin.getHeight(), BufferedImage.TYPE_INT_RGB)));
		} catch (Exception e) {
			// TODO: handle exception
		}
		painelIconeLogin.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 45));
		painelIconeLogin.add(lblIconeLogin);
		
		// Painel do formulario de login
		JPanel painelFormLogin = new JPanel();
		painelLogin.add(painelFormLogin, BorderLayout.EAST);
		painelFormLogin.setLayout(new MigLayout("", "[][][][]", "[grow][][][grow][][grow][][grow]"));
		
		JLabel lblTituloLogin = new JLabel("TELA DE LOGIN");
		lblTituloLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		painelFormLogin.add(lblTituloLogin, "cell 0 0 4 1,alignx center");
		
		JLabel lblUsuario = new JLabel("Usu\u00E1rio");
		painelFormLogin.add(lblUsuario, "flowx,cell 0 3 4 1");
		
		JLabel lblSenha = new JLabel("Senha");
		painelFormLogin.add(lblSenha, "flowx,cell 0 5 4 1");
		
		buttonAutenticar = new JButton("Autenticar");
		painelFormLogin.add(buttonAutenticar, "flowx,cell 0 7 4 1,growx");
		
		fieldUsuario = new JTextField();
		painelFormLogin.add(fieldUsuario, "cell 0 3 4 1,growx");
		fieldUsuario.setColumns(10);
		
		fieldSenha = new JTextField();
		painelFormLogin.add(fieldSenha, "cell 0 5 4 1,growx");
		fieldSenha.setColumns(10);
		
		buttonLimparLogin = new JButton("Limpar");
		painelFormLogin.add(buttonLimparLogin, "cell 0 7 3 1,growx");
		
		// Painel de cadastro
		painelCadastro = new JPanel();
		painelCadastro.setBackground(SystemColor.control);
		painelPrincipal.add(painelCadastro, "telaCadastro");
		painelCadastro.setLayout(new MigLayout("", "[][][][]", "[][][grow][grow][grow][grow]"));
		
		// Label para a foto de perfil
		lblImgPerfil = new JLabel("Escolha a foto de perfil");
		lblImgPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		painelCadastro.add(lblImgPerfil, "cell 0 0,alignx center");

		JLabel lblTituloCadastro = new JLabel("Cadastro de Clientes");
		lblTituloCadastro.setFont(new Font("Tahoma", Font.BOLD, 20));
		painelCadastro.add(lblTituloCadastro, "cell 3 0");
		
		JLabel lblNome = new JLabel("Nome");
		painelCadastro.add(lblNome, "flowx,cell 0 2, spanx 3");
		
		JLabel lblSexo = new JLabel("Sexo");
		painelCadastro.add(lblSexo, "flowx,cell 3 2");
		
		radioButtonMasc = new JRadioButton("M");
		painelCadastro.add(radioButtonMasc, "cell 3 2");
		
		radioButtonFem = new JRadioButton("F");
		painelCadastro.add(radioButtonFem, "cell 3 2");
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButtonFem);
		buttonGroup.add(radioButtonMasc);
		
		JLabel lblCpf = new JLabel("CPF");
		painelCadastro.add(lblCpf, "flowx,cell 0 3, spanx 3");
		
		JLabel lblProfissao = new JLabel("Profiss\u00E3o");
		painelCadastro.add(lblProfissao, "flowx,cell 3 3");
		
		comboBoxProfissao = new JComboBox<>();
		comboBoxProfissao.addItem("Médico");
		comboBoxProfissao.addItem("Professor");
		painelCadastro.add(comboBoxProfissao, "cell 3 3,growx");
		
		JLabel lblEndereco = new JLabel("Endere\u00E7o");
		painelCadastro.add(lblEndereco, "flowx,cell 0 4 4 1");
		
		buttonGravar = new JButton("Gravar");
		buttonGravar.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/figuras/ok1.png")));
		painelCadastro.add(buttonGravar, "flowx,cell 0 5");
		
		buttonLimparCadastro = new JButton("Limpar");
		buttonLimparCadastro.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/figuras/clean.png")));
		painelCadastro.add(buttonLimparCadastro, "cell 0 5");
		
		fieldNome = new JTextField();
		painelCadastro.add(fieldNome, "cell 0 2,growx");
		fieldNome.setColumns(10);
		
		fieldCpf = new JTextField();
		painelCadastro.add(fieldCpf, "cell 0 3,growx");
		fieldCpf.setColumns(10);
		
		fieldEndereco = new JTextField();
		painelCadastro.add(fieldEndereco, "cell 0 4 4 1,growx");
		fieldEndereco.setColumns(10);
		
	}

	public JPanel getPainelPrincipal() {
		return painelPrincipal;
	}

	public void setPainelPrincipal(JPanel painelPrincipal) {
		this.painelPrincipal = painelPrincipal;
	}

	public JPanel getPainelLogin() {
		return painelLogin;
	}

	public void setPainelLogin(JPanel painelLogin) {
		this.painelLogin = painelLogin;
	}

	public JPanel getPainelCadastro() {
		return painelCadastro;
	}

	public void setPainelCadastro(JPanel painelCadastro) {
		this.painelCadastro = painelCadastro;
	}

	public JTextField getFieldUsuario() {
		return fieldUsuario;
	}

	public void setFieldUsuario(JTextField fieldUsuario) {
		this.fieldUsuario = fieldUsuario;
	}

	public JTextField getFieldSenha() {
		return fieldSenha;
	}

	public void setFieldSenha(JTextField fieldSenha) {
		this.fieldSenha = fieldSenha;
	}

	public JTextField getFieldNome() {
		return fieldNome;
	}

	public void setFieldNome(JTextField fieldNome) {
		this.fieldNome = fieldNome;
	}

	public JTextField getFieldCpf() {
		return fieldCpf;
	}

	public void setFieldCpf(JTextField fieldCpf) {
		this.fieldCpf = fieldCpf;
	}

	public JTextField getFieldEndereco() {
		return fieldEndereco;
	}

	public void setFieldEndereco(JTextField fieldEndereco) {
		this.fieldEndereco = fieldEndereco;
	}

	public JButton getButtonAutenticar() {
		return buttonAutenticar;
	}

	public void setButtonAutenticar(JButton buttonAutenticar) {
		this.buttonAutenticar = buttonAutenticar;
	}

	public JButton getButtonLimparLogin() {
		return buttonLimparLogin;
	}

	public void setButtonLimparLogin(JButton buttonLimparLogin) {
		this.buttonLimparLogin = buttonLimparLogin;
	}

	public JLabel getLblImgPerfil() {
		return lblImgPerfil;
	}

	public void setLblImgPerfil(JLabel lblImgPerfil) {
		this.lblImgPerfil = lblImgPerfil;
	}

	public JRadioButton getRadioButtonMasc() {
		return radioButtonMasc;
	}

	public void setRadioButtonMasc(JRadioButton radioButtonMasc) {
		this.radioButtonMasc = radioButtonMasc;
	}

	public JRadioButton getRadioButtonFem() {
		return radioButtonFem;
	}

	public void setRadioButtonFem(JRadioButton radioButtonFem) {
		this.radioButtonFem = radioButtonFem;
	}

	public JButton getButtonGravar() {
		return buttonGravar;
	}

	public void setButtonGravar(JButton buttonGravar) {
		this.buttonGravar = buttonGravar;
	}

	public JButton getButtonLimparCadastro() {
		return buttonLimparCadastro;
	}

	public void setButtonLimparCadastro(JButton buttonLimparCadastro) {
		this.buttonLimparCadastro = buttonLimparCadastro;
	}

	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	public void setButtonGroup(ButtonGroup buttonGroup) {
		this.buttonGroup = buttonGroup;
	}

	public JComboBox<String> getComboBoxProfissao() {
		return comboBoxProfissao;
	}

	public void setComboBoxProfissao(JComboBox<String> comboBoxProfissao) {
		this.comboBoxProfissao = comboBoxProfissao;
	}

	public JMenuItem getMenuItemCadastrar() {
		return menuItemCadastrar;
	}

	public void setMenuItemCadastrar(JMenuItem menuItemCadastrar) {
		this.menuItemCadastrar = menuItemCadastrar;
	}

	public JMenuItem getMenuItemAutenticar() {
		return menuItemAutenticar;
	}

	public void setMenuItemAutenticar(JMenuItem menuItemAutenticar) {
		this.menuItemAutenticar = menuItemAutenticar;
	}

	public JMenuItem getMenuItemSair() {
		return menuItemSair;
	}

	public void setMenuItemSair(JMenuItem menuItemSair) {
		this.menuItemSair = menuItemSair;
	}
	
	// Caixas de dialogo
	
	public void mensagemAutenticacao(boolean msg) {
		if(msg) {
			JOptionPane.showMessageDialog(this, "Usuário autenticado", "Sucesso", JOptionPane.PLAIN_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Usuário não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void mensagemErroFormCadastro() {
		JOptionPane.showMessageDialog(this, "Preencha todos os campos, por favor", "Erro", JOptionPane.ERROR_MESSAGE);
	}
	
	public void mensagemCadastro(boolean msg) {
		if(msg) {
			JOptionPane.showMessageDialog(this, "Usuário cadastrado", "Sucesso", JOptionPane.PLAIN_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Erro no cadastro", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

}
