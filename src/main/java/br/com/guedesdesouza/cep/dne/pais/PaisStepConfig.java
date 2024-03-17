package br.com.guedesdesouza.cep.dne.pais;

import br.com.guedesdesouza.cep.dne.model.PaisDNE;
import br.com.guedesdesouza.cep.model.Pais;
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
public class PaisStepConfig {

    private final PlatformTransactionManager platformTransactionManager;
    private final JobRepository jobRepository;

    @Bean
    Step importaPaises(
            ItemReader<PaisDNE> reader,
            ItemProcessor<PaisDNE, Pais> processor,
            ItemWriter<Pais> writer) {
        return new StepBuilder("importaPaises", jobRepository)
                .<PaisDNE, Pais>chunk(500, platformTransactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
