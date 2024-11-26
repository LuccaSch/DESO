package isi.deso.tp_spring.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("isi.deso.tp_spring.domain")
@EnableJpaRepositories("isi.deso.tp_spring.repos")
@EnableTransactionManagement
public class DomainConfig {
}
