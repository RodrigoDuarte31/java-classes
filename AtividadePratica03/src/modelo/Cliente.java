package modelo;

import java.io.FileInputStream;

public class Cliente {
	
	private String nome;
	private String cpf;
	private String endereco;
	private String sexo;
	private String profissao;
	private FileInputStream fotoPerfil;
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public Cliente(String nome, String cpf, String endereco, String sexo, String profissao, FileInputStream fotoPerfil) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.sexo = sexo;
		this.profissao = profissao;
		this.fotoPerfil = fotoPerfil;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public FileInputStream getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(FileInputStream fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

}
