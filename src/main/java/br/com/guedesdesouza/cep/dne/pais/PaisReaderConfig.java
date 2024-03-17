package br.com.guedesdesouza.cep.dne.pais;

import br.com.guedesdesouza.cep.dne.model.PaisDNE;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class PaisReaderConfig {

    @Bean
    FlatFileItemReader<PaisDNE> paisReader() {
        return new FlatFileItemReaderBuilder<PaisDNE>()
                .name("paisReader")
                .linesToSkip(1)
                .resource(new FileSystemResource("E:\\eDNE_Basico_1812\\Fixo\\DNE_GU_PAISES.TXT"))
                .encoding("ISO-8859-1")
                .fixedLength()
                .strict(false)
                .columns(
                        new Range(1, 1),
                        new Range(2, 3),
                        new Range(4, 6),
                        new Range(7, 78),
                        new Range(79, 150),
                        new Range(151, 222),
                        new Range(223, 258)
                )
                .names(
                        "tipoRegistro",
                        "sigla1",
                        "sigla2",
                        "nomePortugues",
                        "nomeIngles",
                        "nomeFrances",
                        "abreviaturaECT"
                )
                .targetType(PaisDNE.class)
                .build();
    }

}
