package br.com.guedesdesouza.cep.repository;

import br.com.guedesdesouza.cep.model.Bairro;
import br.com.guedesdesouza.cep.util.CacheStore;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class BairroRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CacheStore<Long, Bairro> bairrosCodigoCache;

    public Bairro getBairroByCodigo(Long codigo) {
        return bairrosCodigoCache.get(codigo, () -> getBairroByCodigoDatabase(codigo));
    }

    private Bairro getBairroByCodigoDatabase(Long codigo) {
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
                                return Bairro.builder()
                                        .id(rs.getLong("bai_id"))
                                        .codigo(rs.getLong("bai_codigo"))
                                        .nome(rs.getString("bai_nome"))
                                        .abreviacao(rs.getString("bai_abreviacao"))
                                        .cidadeId(rs.getLong("cid_id"))
                                        .build();
                            }
                            , codigo);
        } catch (EmptyResultDataAccessException exception) {
            return null;
        }
    }

}
