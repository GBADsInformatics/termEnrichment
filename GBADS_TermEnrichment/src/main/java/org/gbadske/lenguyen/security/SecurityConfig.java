package org.gbadske.lenguyen.security;

import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${gbads.api.username}")
	String userName;
	@Value("${gbads.api.password}")
	String password;
	@Value("${api.basePath}")
	String basePath;
	
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.withUsername(userName).password(passwordEncoder.encode(password))
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
                  		.authorizeHttpRequests(requests -> requests.requestMatchers(mvc.pattern(basePath + "/swagger-ui/**")).permitAll())
                  		.authorizeHttpRequests(requests -> requests.requestMatchers(mvc.pattern(basePath + "/javainuse-openapi/**")).permitAll())
                  		.authorizeHttpRequests(requests -> requests.requestMatchers(mvc.pattern(basePath + "/v1/auth/**")).permitAll())
                  		.authorizeHttpRequests(requests -> requests.requestMatchers(mvc.pattern(basePath + "/docs")).permitAll())
                  		.authorizeHttpRequests(requests -> requests.requestMatchers(mvc.pattern(basePath + "/v3/api-docs/**")).permitAll())
                		.authorizeHttpRequests(requests -> requests.requestMatchers(mvc.pattern(basePath + "/list")).permitAll())
                		.authorizeHttpRequests(requests -> requests.requestMatchers(mvc.pattern(basePath + "/postGetEnrichedTerm")).permitAll())
                		.authorizeHttpRequests(requests -> requests.requestMatchers(mvc.pattern(basePath + "/insertAllSpeciesTerm")).authenticated())
                        .authorizeHttpRequests(requests -> requests.requestMatchers(mvc.pattern(basePath + "/insertUpdateSpeciesTerm")).authenticated())
                        .authorizeHttpRequests(requests -> requests.requestMatchers(mvc.pattern(basePath + "/insertUpdateSpeciesTerms")).authenticated())
                        .authorizeHttpRequests(requests -> requests.requestMatchers(mvc.pattern(basePath + "/deleteAllSpeciesTerms")).authenticated())
                        .authorizeHttpRequests(requests -> requests.requestMatchers(mvc.pattern(basePath + "/deleteSpeciesTermById")).authenticated())
                        .authorizeHttpRequests(requests -> requests.requestMatchers(mvc.pattern(basePath + "/deleteSpeciesTermByIdList")).authenticated())
                        .authorizeHttpRequests(requests -> requests.requestMatchers(mvc.pattern(basePath + "/findTermById")).authenticated())
                        .authorizeHttpRequests(requests -> requests.requestMatchers(mvc.pattern(basePath + "/listAllTerms")).authenticated())
                        .httpBasic(Customizer.withDefaults()).formLogin(form -> form
                            .loginPage(basePath + "/login")
                            .permitAll()
                        );
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
