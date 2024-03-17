package br.com.guedesdesouza.cep.dne.tipologradouro;

import br.com.guedesdesouza.cep.dne.model.TipoLogradouroDNE;
import br.com.guedesdesouza.cep.dne.model.UnidadeFederacaoDNE;
import br.com.guedesdesouza.cep.model.Estado;
import br.com.guedesdesouza.cep.model.TipoLogradouro;
import br.com.guedesdesouza.cep.repository.EstadoRepository;
import br.com.guedesdesouza.cep.repository.PaisRepository;
import br.com.guedesdesouza.cep.repository.TipoLogradouroRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class TipoLogradouroProcessorConfig {

    @Bean
    ItemProcessor<TipoLogradouroDNE, TipoLogradouro> tipoLogradouroProcessor() {
        return item -> TipoLogradouro.builder()
                .codigo(item.chaveDNE())
                .nome(item.nomeOficial().trim())
                .abreviacao(item.abreviacao())
                .build();
    }

}
