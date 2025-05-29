package org.serratec.backend.enums;



import org.serratec.backend.exception.EnunException;

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
		throw new EnunException("Categoria farmacêutica inválida");
	}
}