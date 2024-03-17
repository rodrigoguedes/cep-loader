package br.com.guedesdesouza.cep.repository;

import br.com.guedesdesouza.cep.model.TipoLogradouro;
import br.com.guedesdesouza.cep.util.CacheStore;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TipoLogradouroRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CacheStore<Long, TipoLogradouro> tipoLogradouroCodigoCache;

    public TipoLogradouro getTipoLogradouroByCodigo(Long codigo) {
        return tipoLogradouroCodigoCache.get(codigo, () -> getTipoLogradouroByCodigoDatabase(codigo));
    }

    private TipoLogradouro getTipoLogradouroByCodigoDatabase(Long codigo) {
        try {
            return jdbcTemplate
                    .queryForObject("""
                        SELECT
                            *
                        FROM
                            cep_tipos_logradouro
                        WHERE
                            tpl_codigo = ?
                    """,
                    (rs, rowNum) -> {
                        return TipoLogradouro.builder()
                                .id(rs.getLong("tpl_id"))
                                .nome(rs.getString("tpl_nome"))
                                .abreviacao(rs.getString("tpl_abreviacao"))
                                .build();
                    }
                    , codigo);
        } catch (EmptyResultDataAccessException exception) {
            return null;
        }
    }

}
