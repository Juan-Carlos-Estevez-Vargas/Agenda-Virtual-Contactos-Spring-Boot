package com.estevez.agenda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * " SecurityFilterChain " define una cadena de filtros que se puede comparar
 * con una HttpServletRequest.
 * 
 * Como se menciona en la configuración a continuación, las URL que coinciden
 * con "/usuario/**" son accesibles para los usuarios que tienen el rol
 * "USUARIO" o "ADMIN" , mientras que las URL que coinciden con "/admin/**" solo
 * son accesibles para usuarios que tengan el rol "ADMIN" .
 * 
 * Por otro lado, las URL que comienzan con "/registro/**" y "/login/**" son
 * accesibles tanto para usuarios autenticados como no autenticados , o
 * accesibles para todos.
 * 
 * @author Juan Carlos Estevez Vargas.
 *
 */
@Configuration
@EnableWebSecurity
public class SpringSecurity {

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests((requests) -> requests.requestMatchers("/registro/**").permitAll()
				.requestMatchers("/login/**").permitAll().requestMatchers("/usuario/**").hasAnyRole("USER", "ADMIN")
				.requestMatchers("/admin/**").hasAnyRole("ADMIN").anyRequest().authenticated())
				.formLogin((form) -> form.loginPage("/login").loginProcessingUrl("/login")
						.defaultSuccessUrl("/usuario/home").permitAll())
				.logout((logout) -> logout.permitAll()).exceptionHandling().accessDeniedPage("/acceso-denegado");
		return http.build();
	}

}
