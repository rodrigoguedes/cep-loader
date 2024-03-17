package br.com.guedesdesouza.cep.dne.pais;

import br.com.guedesdesouza.cep.model.Pais;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PaisWriterConfig {

    @Bean
    JdbcBatchItemWriter<Pais> paisWrite(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Pais>()
                .dataSource(dataSource)
                .sql("""
                        INSERT INTO cep_paises (
                            pai_sigla,
                            pai_nome
                        ) VALUES (
                            :sigla,
                            :nome
                        )
                    """)
                .beanMapped()
                .build();
    }

}
