package br.com.guedesdesouza.cep.model;

import lombok.Builder;

@Builder
public record Pais(
        Long id,
        String sigla,
        String nome) {
}
