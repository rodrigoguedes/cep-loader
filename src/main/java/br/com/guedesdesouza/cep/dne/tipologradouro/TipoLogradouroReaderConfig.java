package br.com.guedesdesouza.cep.dne.tipologradouro;

import br.com.guedesdesouza.cep.dne.model.TipoLogradouroDNE;
import br.com.guedesdesouza.cep.dne.model.UnidadeFederacaoDNE;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class TipoLogradouroReaderConfig {

    @Bean
    FlatFileItemReader<TipoLogradouroDNE> tipoLogradouroReader() {
        return new FlatFileItemReaderBuilder<TipoLogradouroDNE>()
                .name("tipoLogradouroReader")
                .linesToSkip(1)
                .resource(new FileSystemResource("E:\\eDNE_Basico_1812\\Fixo\\DNE_GU_TIPOS_LOGRADOURO.TXT"))
                .encoding("ISO-8859-1")
                .fixedLength()
                .strict(false)
                .columns(
                        new Range(1, 1),
                        new Range(5, 7),
                        new Range(8, 79),
                        new Range(80, 94)
                )
                .names(
                        "tipoRegistro",
                        "chaveDNE",
                        "nomeOficial",
                        "abreviacao"
                )
                .targetType(TipoLogradouroDNE.class)
                .build();
    }

}
