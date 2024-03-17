package br.com.guedesdesouza.cep.model;

import lombok.Builder;

@Builder
public record Estado(
        Long id,
        String sigla,
        String nome,
        Long paisId
) {
}
