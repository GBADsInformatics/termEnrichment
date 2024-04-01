package org.gbadske.lenguyen.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.withUsername("le@uoguelph.ca").password(passwordEncoder.encode("gbads2024!"))
                .roles("USER", "ADMIN").build();
        return new InMemoryUserDetailsManager(admin);
    }
	@Bean
	MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
		return new MvcRequestMatcher.Builder(introspector);
	}
	
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        //Builder pattern.
        http.csrf(c -> c.disable()).cors(c->c.disable())
                .headers(header -> header.frameOptions(frame -> frame.disable()))
                  		.authorizeHttpRequests(requests -> requests.requestMatchers(mvc.pattern("/swagger-ui/**")).permitAll())
                  		.authorizeHttpRequests(requests -> requests.requestMatchers(mvc.pattern("/javainuse-openapi/**")).permitAll())
                  		.authorizeHttpRequests(requests -> requests.requestMatchers(mvc.pattern("/api/v1/auth/**")).permitAll())
                  		.authorizeHttpRequests(requests -> requests.requestMatchers(mvc.pattern("/v3/api-docs/**")).permitAll())
                		.authorizeHttpRequests(requests -> requests.requestMatchers(mvc.pattern("/api/list")).permitAll())
                		.authorizeHttpRequests(requests -> requests.requestMatchers(mvc.pattern("/api/postGetEnrichedTerm")).permitAll())
                        .authorizeHttpRequests(requests -> requests.requestMatchers(mvc.pattern("/api/insertSpeciesTerm")).permitAll())
                        .authorizeHttpRequests(requests -> requests.requestMatchers(mvc.pattern("/api/insertAllSpeciesTerm")).permitAll())
                        .httpBasic(Customizer.withDefaults()).formLogin(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
