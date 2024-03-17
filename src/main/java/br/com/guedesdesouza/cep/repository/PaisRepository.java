package br.com.guedesdesouza.cep.repository;

import br.com.guedesdesouza.cep.model.Pais;
import br.com.guedesdesouza.cep.util.CacheStore;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PaisRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CacheStore<String, Pais> paisSiglaCache;

    public Pais getPaisBySigla(String sigla) {
        return paisSiglaCache.get(sigla, () -> getPaisBySiglaDatabase(sigla));
    }

    private Pais getPaisBySiglaDatabase(String sigla) {
        try {
            return jdbcTemplate
                    .queryForObject("""
                        SELECT
                            *
                        FROM
                            cep_paises
                        WHERE
                            pai_sigla = ?
                    """,
                    (rs, rowNum) -> {
                        return Pais.builder()
                                .id(rs.getLong("pai_id"))
                                .nome(rs.getString("pai_nome"))
                                .sigla(rs.getString("pai_sigla"))
                                .build();
                    }
                    , sigla);
        } catch (EmptyResultDataAccessException exception) {
            return null;
        }
    }

}
