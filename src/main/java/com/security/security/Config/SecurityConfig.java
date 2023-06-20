package com.security.security.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //Habilita a configuração de segurança do Spring
public class SecurityConfig {
    
    /*
     * A segurança no Spring Security se dá por meio de filtros,
     * ou seja, tudo que é requisitado deve passar pelo filtro de segurança,
     * verificar se é autenticada, e depois repassar para os controladores
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> {
                auth.requestMatchers("/public").permitAll();
                auth.requestMatchers("/logout").permitAll();
                auth.anyRequest().authenticated(); //Para fazer qualquer requisição deve estar autenticado
            }
        ).oauth2Login(Customizer.withDefaults())
        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
        .build();
    }

}
