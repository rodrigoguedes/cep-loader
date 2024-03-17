package br.com.guedesdesouza.cep.dne.pais;

import br.com.guedesdesouza.cep.dne.model.PaisDNE;
import br.com.guedesdesouza.cep.model.Pais;
import br.com.guedesdesouza.cep.repository.PaisRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class PaisProcessorConfig {

    @Bean
    ItemProcessor<PaisDNE, Pais> paisProcessor() {
        return item -> Pais.builder()
                .sigla(item.sigla1().trim())
                .nome(item.nomePortugues().trim())
                .build();
    }

}
