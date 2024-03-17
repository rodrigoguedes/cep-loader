package br.com.guedesdesouza.cep.dne.model;

public record PaisDNE(
        String tipoRegistro,
        String sigla1,
        String sigla2,
        String nomePortugues,
        String nomeIngles,
        String nomeFrances,
        String abreviaturaECT) {
}
