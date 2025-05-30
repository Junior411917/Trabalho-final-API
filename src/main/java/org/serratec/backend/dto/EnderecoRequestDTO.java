package org.serratec.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.serratec.backend.entity.Endereco;

public class EnderecoRequestDTO {
	@NotBlank(message = "O CEP é obrigatório.")
	@Pattern(regexp = "\\d{8}", message = "CEP inválido. Deve conter exatamente 8 dígitos numéricos.")
	private String cep;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;
	
	public EnderecoRequestDTO() {
	}

	public EnderecoRequestDTO(Endereco endereco) {
		this.cep = endereco.getCep();
		this.logradouro = endereco.getLogradouro();
		this.bairro = endereco.getBairro();
		this.localidade = endereco.getLocalidade();
		this.uf = endereco.getUf();
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
}
