package org.serratec.backend.enums;

import org.serratec.backend.exception.EnumException;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EstadoDoPedido {
	PENDENTE, PROCESSANDO, ENVIADO, ENTREGUE, CANCELADO;

	@JsonCreator
	public static EstadoDoPedido verificarEstadoDoPedido(String valor) {
		for (EstadoDoPedido estado : EstadoDoPedido.values()) {
			if (valor.equals(estado.name())) {
				return estado;
			}
		}
		throw new EnumException("Estado do pedido inválido");
	}
}
