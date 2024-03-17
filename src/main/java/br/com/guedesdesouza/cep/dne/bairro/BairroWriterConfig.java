package br.com.guedesdesouza.cep.dne.bairro;

import br.com.guedesdesouza.cep.model.Bairro;
import br.com.guedesdesouza.cep.model.Cidade;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BairroWriterConfig {

    @Bean
    JdbcBatchItemWriter<Bairro> bairroWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Bairro>()
                .dataSource(dataSource)
                .sql("""
                        INSERT INTO cep_bairros (
                            bai_codigo,
                            bai_nome,
                            bai_abreviacao,
                            cid_id
                        ) VALUES (
                            :codigo,
                            :nome,
                            :abreviacao,
                            :cidadeId
                        )
                    """)
                .beanMapped()
                .build();
    }

}
