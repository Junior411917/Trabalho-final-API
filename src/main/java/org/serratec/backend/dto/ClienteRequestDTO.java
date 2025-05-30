package org.serratec.backend.dto;

import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;
import org.serratec.backend.entity.Cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClienteRequestDTO {
	@NotBlank(message = "O nome do cliente não pode ser nulo ou vazio!")
	private String nome;

	@Size(min = 11, max = 11, message = "O telefone deve ter 11 caracteres, Ex: (XX)XXXXX-XXXX")
	@NotBlank(message = "O telefone do cliente não pode ser nulo ou vazio!")
	private String telefone;

	@NotBlank(message = "O email do cliente não pode ser nulo ou vazio!")
	@Email(message = "A formatação do email é invalida!")
	private String email;

	@NotBlank(message = "O CPF do cliente não pode ser nulo ou vazio!")
	@Size(min = 11, max = 11, message = "O CPF deve ter 11 caracteres!")
	@CPF(message = "A formatação do CPF é invalida!")
	private String cpf;

	@NotBlank(message = "O CEP do cliente não pode ser nulo ou vazio!")
	private String cep;

	public ClienteRequestDTO() {
	}

	public ClienteRequestDTO(Cliente cliente) {
		this.nome = cliente.getNome();
		this.telefone = cliente.getTelefone();
		this.email = cliente.getEmail();
		this.cpf = cliente.getCpf();
		this.cep = cliente.getCep();
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
