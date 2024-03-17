package br.com.guedesdesouza.cep.dne.model;

public record TipoLogradouroDNE(
        String tipoRegistro,
        Long chaveDNE,
        String nomeOficial,
        String abreviacao) {
}
