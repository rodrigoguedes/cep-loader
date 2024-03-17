package br.com.guedesdesouza.cep.dne.model;

public record UnidadeFederacaoDNE(
        String tipoRegistro,
        String siglaPais,
        String siglaUnidadeFederacao,
        String chaveUnidadeFederacaoDNE,
        String nomeOficial,
        String abreviatura
) {
}
