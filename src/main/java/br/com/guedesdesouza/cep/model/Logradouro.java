package br.com.guedesdesouza.cep.model;

public record Logradouro(
    Long id,
    Long localidadeId,
    Long tipoLogradouroId,
    Long bairroIdInicial,
    Long bairroIdFinal,
    Long chaveDNE,
    String nomeOficialLogradouro,
    String abreviacaoLogradouro,
    String cep) {
}
