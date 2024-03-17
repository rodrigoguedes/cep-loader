package br.com.guedesdesouza.cep.dne;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@AllArgsConstructor
public class CepLoaderBatchConfig {

    private final PlatformTransactionManager platformTransactionManager;
    private final JobRepository jobRepository;

    @Bean
    Job job(
            Step importaPaises,
            Step importaUnidadesFederacao,
            Step importaTiposLogradouro,
            Step importaLocalidade,
            Step importaBairro) {
        return new JobBuilder("CEP loader job", jobRepository)
                .start(importaPaises)
                .next(importaUnidadesFederacao)
                .next(importaTiposLogradouro)
                .next(importaLocalidade)
                .next(importaBairro)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
