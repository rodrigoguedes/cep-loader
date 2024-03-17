package br.com.guedesdesouza.cep.model;

import lombok.Builder;

@Builder
public record Bairro(
        Long id,
        Long cidadeId,
        Long codigo,
        String nome,
        String abreviacao) {

}
