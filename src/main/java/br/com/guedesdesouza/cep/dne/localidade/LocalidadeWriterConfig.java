package br.com.guedesdesouza.cep.dne.localidade;

import br.com.guedesdesouza.cep.model.Cidade;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LocalidadeWriterConfig {

    @Bean
    JdbcBatchItemWriter<Cidade> localidadeWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Cidade>()
                .dataSource(dataSource)
                .sql("""
                        INSERT INTO cep_cidades (
                            cid_codigo,
                            cid_codigo_ibge,
                            cid_nome,
                            cid_cep,
                            cid_abreviacao,
                            cid_tipo_localidade,
                            est_id
                        ) VALUES (
                            :codigo,
                            :codigoIBGE,
                            :nome,
                            :cep,
                            :abreviacao,
                            :tipoLocalidade,
                            :estadoId
                        )
                    """)
                .beanMapped()
                .build();
    }

}
