package br.com.guedesdesouza.cep.dne.bairro;

import br.com.guedesdesouza.cep.dne.model.BairroDNE;
import br.com.guedesdesouza.cep.model.Bairro;
import br.com.guedesdesouza.cep.repository.CidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class BairroProcessorConfig {

    private final CidadeRepository cidadeRepository;

    @Bean
    ItemProcessor<BairroDNE, Bairro> bairroProcessor() {
        return item -> Bairro.builder()
                .codigo(item.chaveDNE())
                .nome(item.nomeOficialBairro().trim())
                .abreviacao(item.abreviaturaBairro())
                .cidadeId(cidadeRepository.getCidadeByCodigo(item.chaveLocalidade()).id())
                .build();
    }

}
