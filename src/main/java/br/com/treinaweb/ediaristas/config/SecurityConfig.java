package br.com.treinaweb.ediaristas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.treinaweb.ediaristas.core.enums.TipoUsuario;
import br.com.treinaweb.ediaristas.core.filters.AccessTokenRequestFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private AccessTokenRequestFilter accessTokenRequestFilter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Value("${br.com.treinaweb.ediaristas.rememberMe.key}")
    private String rememberMeKey;

    @Value("${br.com.treinaweb.ediaristas.rememberMe.validitySeconds}")
    private int rememberMeValiditySeconds;

    @Bean
    @Order(1)
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatchers(requestMatcherCustomizer ->
            requestMatcherCustomizer
                .requestMatchers("/api/**", "/auth/**")
        )
        .authorizeHttpRequests(authorizeRequestsCustomizer ->
            authorizeRequestsCustomizer
                .anyRequest()
                .permitAll()
        )
        .csrf(csrfCustomizer ->
            csrfCustomizer
                .disable()
        )
        .sessionManagement(sessionManagementCustomizer ->
            sessionManagementCustomizer
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .addFilterBefore(accessTokenRequestFilter, UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling(exceptionHandlingCustomizer ->
            exceptionHandlingCustomizer
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
        )
        .cors(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatchers(requestMatcherCustomizer ->
            requestMatcherCustomizer
                .requestMatchers("/admin/**")
        )
        .authorizeHttpRequests(authorizeRequestsCustomizer ->
            authorizeRequestsCustomizer
                .requestMatchers("/admin/resetar-senha/**").permitAll()
                .anyRequest()
                .hasAnyAuthority(TipoUsuario.ADMIN.name())
        )
        .formLogin(formLoginCustomizer ->
            formLoginCustomizer
                .loginPage("/admin/login")
                .usernameParameter("email")
                .passwordParameter("senha")
                .defaultSuccessUrl("/admin/servicos")
                .permitAll()
        )
        .logout(logoutCustomizer ->
            logoutCustomizer
                .logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout", "GET"))
                .logoutSuccessUrl("/admin/login")
        )
        .rememberMe(rememberMeCustomizer ->
            rememberMeCustomizer
                .rememberMeParameter("lembrar-me")
                .tokenValiditySeconds(rememberMeValiditySeconds)
                .key(rememberMeKey)
        )
        .exceptionHandling(exceptionHandlingCustomizer ->
            exceptionHandlingCustomizer
                .accessDeniedPage("/admin/login"));

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
            .requestMatchers("/webjars/**", "/img/**");
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
