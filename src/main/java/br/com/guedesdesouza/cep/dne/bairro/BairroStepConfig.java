package br.com.guedesdesouza.cep.dne.bairro;

import br.com.guedesdesouza.cep.dne.model.BairroDNE;
import br.com.guedesdesouza.cep.dne.model.LocalidadeDNE;
import br.com.guedesdesouza.cep.model.Bairro;
import br.com.guedesdesouza.cep.model.Cidade;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@AllArgsConstructor
@Configuration
public class BairroStepConfig {

    private final PlatformTransactionManager platformTransactionManager;
    private final JobRepository jobRepository;

    @Bean
    Step importaBairro(
            @Qualifier("bairroReader") ItemReader<BairroDNE> reader,
            @Qualifier("bairroProcessor") ItemProcessor<BairroDNE, Bairro> processor,
            @Qualifier("bairroWriter") ItemWriter<Bairro> writer) {
        return new StepBuilder("importaBairro", jobRepository)
                .<BairroDNE, Bairro>chunk(500, platformTransactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
