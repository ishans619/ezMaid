package com.ezmaid.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ezmaid.util.AppConstants;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final TokenAuthenticationFilter tokenAuthenticationFilter;

    public WebSecurityConfig(TokenAuthenticationFilter tokenAuthenticationFilter) {
		super();
		this.tokenAuthenticationFilter = tokenAuthenticationFilter;
	}

	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
//                .requestMatchers(HttpMethod.POST, "/home").hasAnyAuthority(AppConstants.ADMIN, AppConstants.SUPER_ADMIN, AppConstants.CUSTOMER, AppConstants.MAID)
//                .requestMatchers(HttpMethod.GET, "/api/users/me").hasAnyAuthority(ADMIN, SUPER_ADMIN, CUSTOMER, MAID)
        		.requestMatchers("/admins/**").hasAnyAuthority(AppConstants.ADMIN, AppConstants.SUPER_ADMIN)
        		.requestMatchers("/maids/**").hasAnyAuthority(AppConstants.MAID, AppConstants.ADMIN, AppConstants.SUPER_ADMIN, AppConstants.CUSTOMER)
        		.requestMatchers("/customers/**").hasAnyAuthority(AppConstants.CUSTOMER, AppConstants.ADMIN, AppConstants.SUPER_ADMIN)
        		.requestMatchers("/requests/**").hasAnyAuthority(AppConstants.CUSTOMER, AppConstants.MAID)
        		.requestMatchers("/home/**", "/auth/**").permitAll()
        		.requestMatchers("/", "/error", "/csrf", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors().and().csrf().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
