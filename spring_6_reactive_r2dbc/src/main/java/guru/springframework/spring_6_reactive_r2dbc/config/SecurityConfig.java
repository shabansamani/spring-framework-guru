package guru.springframework.spring_6_reactive_r2dbc.config;

import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

  @Bean
  SecurityWebFilterChain actuatorSecurityFilterChain(ServerHttpSecurity http) {
    http.securityMatcher(EndpointRequest.toAnyEndpoint())
        .authorizeExchange(authorize -> authorize.anyExchange().permitAll());

    return http.build();
  }

  @Bean
  SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    http.csrf(csrf -> csrf.disable().authorizeExchange(exchange -> exchange
        .anyExchange().authenticated())
        .oauth2ResourceServer(server -> server.jwt(Customizer.withDefaults())));

    return http.build();
  }
}
