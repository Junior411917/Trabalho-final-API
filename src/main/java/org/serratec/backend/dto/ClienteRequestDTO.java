package org.serratec.backend.dto;

import org.hibernate.validator.constraints.br.CPF;
import org.serratec.backend.entity.Cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClienteRequestDTO {
//Validação dos atributos de cliente	
	@NotBlank
	private String nome;

	@NotBlank
	private String telefone;

	@Email
	private String email;

	@CPF
	private String cpf;

	public ClienteRequestDTO() {

	}

	public ClienteRequestDTO(Cliente cliente) {

		this.nome = cliente.getNome();
		this.telefone = cliente.getTelefone();
		this.email = cliente.getEmail();
		this.cpf = cliente.getCpf();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
