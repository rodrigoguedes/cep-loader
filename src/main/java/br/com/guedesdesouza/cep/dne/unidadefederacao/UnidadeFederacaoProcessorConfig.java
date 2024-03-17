package br.com.guedesdesouza.cep.dne.unidadefederacao;

import br.com.guedesdesouza.cep.dne.model.UnidadeFederacaoDNE;
import br.com.guedesdesouza.cep.model.Estado;
import br.com.guedesdesouza.cep.repository.PaisRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class UnidadeFederacaoProcessorConfig {

    private final PaisRepository paisRepository;

    @Bean
    ItemProcessor<UnidadeFederacaoDNE, Estado> unidadeFederacaoProcessor() {
        return item -> Estado.builder()
                .sigla(item.siglaUnidadeFederacao().trim())
                .nome(item.nomeOficial().trim())
                .paisId(paisRepository.getPaisBySigla(item.siglaPais()).id())
                .build();
    }

}




