package br.com.guedesdesouza.cep.repository;

import br.com.guedesdesouza.cep.model.Cidade;
import br.com.guedesdesouza.cep.util.CacheStore;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CidadeRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CacheStore<Long, Cidade> cidadesCodigoCache;

    public Cidade getCidadeByCodigo(Long codigo) {
        return cidadesCodigoCache.get(codigo, () -> getCidadeByCodigoDatabase(codigo));
    }

    private Cidade getCidadeByCodigoDatabase(Long codigo) {
        try {
            return jdbcTemplate
                    .queryForObject("""
                        SELECT
                            *
                        FROM
                            cep_cidades
                        WHERE
                            cid_codigo = ?
                    """,
                            (rs, rowNum) -> {
                                return Cidade.builder()
                                        .id(rs.getLong("cid_id"))
                                        .codigo(rs.getLong("cid_codigo"))
                                        .codigoIBGE(rs.getLong("cid_codigo_ibge"))
                                        .nome(rs.getString("cid_nome"))
                                        .cep(rs.getString("cid_cep"))
                                        .abreviacao(rs.getString("cid_abreviacao"))
                                        .tipoLocalidade(rs.getString("cid_tipo_localidade"))
                                        .estadoId(rs.getLong("est_id"))
                                        .build();
                            }
                            , codigo);
        } catch (EmptyResultDataAccessException exception) {
            return null;
        }
    }

}
