package br.com.guedesdesouza.cep.dne.localidade;

import br.com.guedesdesouza.cep.dne.model.LocalidadeDNE;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class LocalidadeReaderConfig {

    @Bean
    FlatFileItemReader<LocalidadeDNE> localidadeReader() {
        return new FlatFileItemReaderBuilder<LocalidadeDNE>()
                .name("localidadeReader")
                .linesToSkip(1)
                .resource(new FileSystemResource("E:\\eDNE_Basico_1812\\Fixo\\DNE_GU_LOCALIDADES.TXT"))
                .encoding("ISO-8859-1")
                .fixedLength()
                .strict(false)
                .columns(
                        new Range(1, 1),
                        new Range(2, 3),
                        new Range(4, 5),
                        new Range(12, 19),
                        new Range(20, 91),
                        new Range(92, 99),
                        new Range(100, 135),
                        new Range(136, 136),
                        new Range(155, 161)
                )
                .names(
                        "tipoRegistro",
                        "siglaPais",
                        "siglaUnidadeFederacao",
                        "chaveDNE",
                        "nomeOficialLocalidade",
                        "cepLocalidade",
                        "abreviaturaLocalidade",
                        "tipoLocalidade",
                        "codigoIBGE"
                )
                .targetType(LocalidadeDNE.class)
                .build();
    }

}
