package br.com.guedesdesouza.cep.dne.localidade;

import br.com.guedesdesouza.cep.dne.model.LocalidadeDNE;
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
public class LocalidadeStepConfig {

    private final PlatformTransactionManager platformTransactionManager;
    private final JobRepository jobRepository;

    @Bean
    Step importaLocalidade(
            @Qualifier("localidadeReader") ItemReader<LocalidadeDNE> reader,
            @Qualifier("localidadeProcessor") ItemProcessor<LocalidadeDNE, Cidade> processor,
            @Qualifier("localidadeWriter") ItemWriter<Cidade> writer) {
        return new StepBuilder("importaLocalidade", jobRepository)
                .<LocalidadeDNE, Cidade>chunk(500, platformTransactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
