package br.com.guedesdesouza.cep.dne.unidadefederacao;

import br.com.guedesdesouza.cep.model.Estado;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class UnidadeFederacaoWriterConfig {

    @Bean
    JdbcBatchItemWriter<Estado> unidadeFederacaoWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Estado>()
                .dataSource(dataSource)
                .sql("""
                        INSERT INTO cep_estados (
                            est_sigla,
                            est_nome,
                            pai_id
                        ) VALUES (
                            :sigla,
                            :nome,
                            :paisId
                        )
                    """)
                .beanMapped()
                .build();
    }

}
