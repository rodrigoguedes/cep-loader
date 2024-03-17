package br.com.guedesdesouza.cep.dne.model;

public record LocalidadeDNE(

    String tipoRegistro,
    String siglaPais,
    String siglaUnidadeFederacao,
    Long chaveDNE,
    String nomeOficialLocalidade,
    String cepLocalidade,
    String abreviaturaLocalidade,
    String tipoLocalidade,
    Long codigoIBGE) {

}
