package br.com.guedesdesouza.cep.dne.tipologradouro;

import br.com.guedesdesouza.cep.model.Estado;
import br.com.guedesdesouza.cep.model.TipoLogradouro;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class TipoLogradouroWriterConfig {

    @Bean
    JdbcBatchItemWriter<TipoLogradouro> tipoLogradouroWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<TipoLogradouro>()
                .dataSource(dataSource)
                .sql("""
                        INSERT INTO cep_tipos_logradouro (
                            tpl_codigo,
                            tpl_nome,
                            tpl_abreviacao
                        ) VALUES (
                            :codigo,
                            :nome,
                            :abreviacao
                        )
                    """)
                .beanMapped()
                .build();
    }

}
