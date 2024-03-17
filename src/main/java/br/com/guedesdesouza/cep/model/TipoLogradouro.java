package br.com.guedesdesouza.cep.model;

import lombok.Builder;

@Builder
public record TipoLogradouro(
        Long id,
        Long codigo,
        String nome,
        String abreviacao) {
}
