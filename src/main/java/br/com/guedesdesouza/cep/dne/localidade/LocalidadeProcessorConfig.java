package br.com.guedesdesouza.cep.dne.localidade;

import br.com.guedesdesouza.cep.dne.model.LocalidadeDNE;
import br.com.guedesdesouza.cep.model.Cidade;
import br.com.guedesdesouza.cep.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class LocalidadeProcessorConfig {

    private final EstadoRepository estadoRepository;

    @Bean
    ItemProcessor<LocalidadeDNE, Cidade> localidadeProcessor() {
        return item -> Cidade.builder()
                .codigo(item.chaveDNE())
                .codigoIBGE(
                        item.codigoIBGE() != null && item.codigoIBGE() > 0 ? item.codigoIBGE() : null
                )
//                .codigoIBGE(
//                        Strings.isBlank(item.codigoIBGE().trim()) ? null : Long.valueOf(item.codigoIBGE().trim())
//                )
                .nome(item.nomeOficialLocalidade().trim())
                .cep(item.cepLocalidade())
                .abreviacao(item.abreviaturaLocalidade())
                .tipoLocalidade(item.tipoLocalidade())
                .estadoId(estadoRepository.getEstadoBySigla(item.siglaUnidadeFederacao()).id())
                .build();
    }

}
