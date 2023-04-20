package org.generation.italy.Progetto_Azienda_Medici.security.config;

import lombok.RequiredArgsConstructor;
import org.generation.italy.Progetto_Azienda_Medici.security.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration  //spring identifica questa come una classe di configurazione
@RequiredArgsConstructor  //costruttore per tutti i campi final
public class ApplicationConfig {

  private final UserRepository repository;

  @Bean
  public UserDetailsService userDetailsService() {
    return username -> repository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  /*@Bean
  public UserDetailsService userDetailsService2() {
    class NonVolevoCrearti implements UserDetailsService{

      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
      }
    }
    NonVolevoCrearti z =new NonVolevoCrearti();
    return z;
    //return username -> repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }*/

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {  //si occupa di fare l'hashing
    return new BCryptPasswordEncoder();
  }

}
