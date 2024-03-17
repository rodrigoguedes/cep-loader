package br.com.guedesdesouza.cep.dne.tipologradouro;

import br.com.guedesdesouza.cep.dne.model.TipoLogradouroDNE;
import br.com.guedesdesouza.cep.dne.model.UnidadeFederacaoDNE;
import br.com.guedesdesouza.cep.model.Estado;
import br.com.guedesdesouza.cep.model.TipoLogradouro;
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
public class TipoLogradouroStepConfig {

    private final PlatformTransactionManager platformTransactionManager;
    private final JobRepository jobRepository;

    @Bean
    Step importaTiposLogradouro(
            @Qualifier("tipoLogradouroReader") ItemReader<TipoLogradouroDNE> reader,
            @Qualifier("tipoLogradouroProcessor") ItemProcessor<TipoLogradouroDNE, TipoLogradouro> processor,
            @Qualifier("tipoLogradouroWriter") ItemWriter<TipoLogradouro> writer) {
        return new StepBuilder("importaTipoLogradouro", jobRepository)
                .<TipoLogradouroDNE, TipoLogradouro>chunk(500, platformTransactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
