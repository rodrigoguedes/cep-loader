package br.com.guedesdesouza.cep.model;

import lombok.Builder;

@Builder
public record Cidade(
        Long id,
        Long estadoId,
        Long codigo,
        Long codigoIBGE,
        String nome,
        String cep,
        String abreviacao,
        String tipoLocalidade
) {
}