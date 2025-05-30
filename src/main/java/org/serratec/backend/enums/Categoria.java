package org.serratec.backend.enums;

import org.serratec.backend.exception.EnumException;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Categoria {
MEDICAMENTOS, SUPLEMENTOS, INFANTIL, DERMOCOSMETICOS, SOCORROS;
	
	@JsonCreator
	public static Categoria verificarCategoria(String valor) {
		for (Categoria categoria : Categoria.values()) {
			if (valor.equals(categoria.name())) {
				return categoria;
			}
		}
		throw new EnumException("Categoria farmacêutica inválida");
	}
}