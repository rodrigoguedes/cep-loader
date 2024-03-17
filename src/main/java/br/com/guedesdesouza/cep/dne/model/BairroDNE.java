package br.com.guedesdesouza.cep.dne.model;

public record BairroDNE(
        String tipoRegistro,
        Long chaveLocalidade,
        Long chaveDNE,
        String nomeOficialBairro,
        String abreviaturaBairro
){
}
