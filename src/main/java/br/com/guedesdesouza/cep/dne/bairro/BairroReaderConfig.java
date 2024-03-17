package br.com.guedesdesouza.cep.dne.bairro;

import br.com.guedesdesouza.cep.dne.model.BairroDNE;
import br.com.guedesdesouza.cep.dne.model.LocalidadeDNE;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class BairroReaderConfig {

    @Bean
    FlatFileItemReader<BairroDNE> bairroReader() {
        return new FlatFileItemReaderBuilder<BairroDNE>()
                .name("bairroReader")
                .linesToSkip(1)
                .resource(new FileSystemResource("E:\\eDNE_Basico_1812\\Fixo\\DNE_GU_BAIRROS.TXT"))
                .encoding("ISO-8859-1")
                .fixedLength()
                .strict(false)
                .columns(
                        new Range(1, 1),
                        new Range(10, 17),
                        new Range(95, 102),
                        new Range(103, 174),
                        new Range(175, 210)
                )
                .names(
                        "tipoRegistro",
                        "chaveLocalidade",
                        "chaveDNE",
                        "nomeOficialBairro",
                        "abreviaturaBairro"
                )
                .targetType(BairroDNE.class)
                .build();
    }

}
