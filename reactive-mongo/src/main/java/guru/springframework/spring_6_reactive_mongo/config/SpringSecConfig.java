package guru.springframework.spring_6_reactive_mongo.config;

import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SpringSecConfig {

  @Bean
  SecurityWebFilterChain actuatorSecurityWebFilterChain(ServerHttpSecurity http) {
    http.securityMatcher(EndpointRequest.toAnyEndpoint())
        .authorizeExchange(authorize -> authorize.anyExchange().permitAll());

    return http.build();
  }

  @Bean
  SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    http.authorizeExchange(exchange -> exchange.anyExchange().authenticated())
        .oauth2ResourceServer(oauth2ResourceServerSpec -> oauth2ResourceServerSpec.jwt(Customizer.withDefaults()))
        .csrf(ServerHttpSecurity.CsrfSpec::disable);

    return http.build();
  }
}
