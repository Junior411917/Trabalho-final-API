package org.serratec.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;
import org.serratec.backend.entity.Cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.serratec.backend.entity.ClientePerfil;

import java.util.HashSet;
import java.util.Set;

@Schema(name = "ClienteRequestDTO", example = """
{
    "nome": "string",
    "telefone": "string",
    "email": "string",
    "senha": "string",
    "cpf": "string",
    "cep": "string",
    "clientePerfis": [
        {
            "perfil": {
                "id": "id"
            }
        },
        {
            "perfil": {
                "id": "id"
            }
        }
    ]
}
""")
public class ClienteRequestDTO {
	@NotBlank(message = "O nome do cliente não pode ser nulo ou vazio!")
	private String nome;

	@Size(min = 11, max = 11, message = "O telefone deve ter 11 caracteres, Ex: (XX)XXXXX-XXXX")
	@NotBlank(message = "O telefone do cliente não pode ser nulo ou vazio!")
	private String telefone;

	@NotBlank(message = "O email do cliente não pode ser nulo ou vazio!")
	@Email(message = "A formatação do email é invalida!")
	private String email;

	@Size(min = 8, message = "A senha deve ter no minimo 8 caracteres!")
	@NotBlank(message = "A senha do cliente não pode ser nula ou vazia!")
	private String senha;

	@NotBlank(message = "O CPF do cliente não pode ser nulo ou vazio!")
	@Size(min = 11, max = 11, message = "O CPF deve ter 11 caracteres!")
	@CPF(message = "A formatação do CPF é invalida!")
	private String cpf;

	@NotBlank(message = "O CEP do cliente não pode ser nulo ou vazio!")
	private String cep;

	private Set<ClientePerfil> clientePerfis = new HashSet<>();

	public ClienteRequestDTO() {
	}

	public ClienteRequestDTO(Cliente cliente) {
		this.nome = cliente.getNome();
		this.telefone = cliente.getTelefone();
		this.email = cliente.getEmail();
		this.cpf = cliente.getCpf();
		this.cep = cliente.getEndereco().getCep();
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Set<ClientePerfil> getClientePerfis() {
		return clientePerfis;
	}

	public void setClientePerfis(Set<ClientePerfil> clientePerfis) {
		this.clientePerfis = clientePerfis;
	}
}
