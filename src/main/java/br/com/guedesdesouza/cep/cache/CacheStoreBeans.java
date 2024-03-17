package br.com.guedesdesouza.cep.cache;

import br.com.guedesdesouza.cep.model.*;
import br.com.guedesdesouza.cep.util.CacheStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheStoreBeans {

    @Bean
    public CacheStore<String, Pais> paisSiglaCache() {
        return new CacheStore<>(300, TimeUnit.SECONDS, 100);
    }

    @Bean
    public CacheStore<String, Estado> estadoSiglaCache() {
        return new CacheStore<>(300, TimeUnit.SECONDS, 100);
    }

    @Bean
    public CacheStore<Long, TipoLogradouro> tipoLogradouroCacheStore() {
        return new CacheStore<>(300, TimeUnit.SECONDS, 100);
    }

    @Bean
    public CacheStore<Long, Cidade> cidadesCacheStore() {
        return new CacheStore<>(300, TimeUnit.SECONDS, 100);
    }

    @Bean
    public CacheStore<Long, Bairro> bairrosCacheStore() {
        return new CacheStore<>(300, TimeUnit.SECONDS, 100);
    }

}
