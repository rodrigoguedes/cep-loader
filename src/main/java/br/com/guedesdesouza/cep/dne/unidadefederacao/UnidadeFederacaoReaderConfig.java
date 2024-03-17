package br.com.guedesdesouza.cep.dne.unidadefederacao;

import br.com.guedesdesouza.cep.dne.model.UnidadeFederacaoDNE;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class UnidadeFederacaoReaderConfig {

    @Bean
    FlatFileItemReader<UnidadeFederacaoDNE> unidadeFederacaoReader() {
        return new FlatFileItemReaderBuilder<UnidadeFederacaoDNE>()
                .name("unidadeFederacaoReader")
                .linesToSkip(1)
                .resource(new FileSystemResource("E:\\eDNE_Basico_1812\\Fixo\\DNE_GU_UNIDADES_FEDERACAO.TXT"))
                .encoding("ISO-8859-1")
                .fixedLength()
                .strict(false)
                .columns(
                        new Range(1, 1),
                        new Range(2, 3),
                        new Range(4, 5),
                        new Range(8, 9),
                        new Range(10, 81),
                        new Range(82, 117)
                )
                .names(
                        "tipoRegistro",
                        "siglaPais",
                        "siglaUnidadeFederacao",
                        "chaveUnidadeFederacaoDNE",
                        "nomeOficial",
                        "abreviatura"
                )
                .targetType(UnidadeFederacaoDNE.class)
                .build();
    }

}
