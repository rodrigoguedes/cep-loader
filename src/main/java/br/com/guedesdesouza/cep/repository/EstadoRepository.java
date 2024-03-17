package br.com.guedesdesouza.cep.repository;

import br.com.guedesdesouza.cep.model.Estado;
import br.com.guedesdesouza.cep.util.CacheStore;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EstadoRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CacheStore<String, Estado> estadoSiglaCache;

    public Estado getEstadoBySigla(String sigla) {
        return estadoSiglaCache.get(sigla, () -> getEstadoBySiglaDatabase(sigla));
    }

    private Estado getEstadoBySiglaDatabase(String sigla) {
        try {
            return jdbcTemplate
                    .queryForObject("""
                    SELECT
                       *
                    FROM
                        cep_estados
                    WHERE
                        est_sigla = ?
                """,
                (rs, rowNum) -> {
                    return Estado.builder()
                            .id(rs.getLong("est_id"))
                            .nome(rs.getString("est_nome"))
                            .sigla(rs.getString("est_sigla"))
                            .paisId(rs.getLong("pai_id"))
                            .build();
                }
                , sigla);
        } catch (EmptyResultDataAccessException exception) {
            return null;
        }
    }

}
