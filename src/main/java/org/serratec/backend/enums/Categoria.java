package org.serratec.backend.enums;

import org.serratec.backend.exception.EnumException;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Categoria {
	MEDICAMENTO,
	SUPLEMENTO,
	INFANTIL,
	DERMOCOSMETICO,
	SOCORRO,
	HIGIENE_PESSOAL,
	SAUDE_BUCAL,
	CUIDADO_COM_O_CABELO,
	ORTOPEDIA,
	NUTRICAO_ESPECIALIZADA,
	HOME_CARE,
	FITOTERAPICO,
	PERFUMARIA,
	BEM_ESTAR_RELAXAMENTO,
	EQUIPAMENTO_MEDICO,
	PRODUTO_NATURAL,
	ARTIGO_ESPORTIVO,
	TESTE_DIAGNOSTICO,
	ALIMENTO_FUNCIONAL,
	ACESSORIO_FARMACEUTICO;

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