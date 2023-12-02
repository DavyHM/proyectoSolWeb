package proyecto.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import proyecto.demo.Model.service.UserService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConf {

  @Autowired
  private UserService userService;

  @Bean
  public static BCryptPasswordEncoder encriptarPassword(){
    return new  BCryptPasswordEncoder();
  }


  @Autowired
  public void criptografiaPass(AuthenticationManagerBuilder auth) throws Exception{
    auth.userDetailsService(userService).passwordEncoder(encriptarPassword());
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
      httpSecurity
      .authorizeHttpRequests(auth -> {
          auth.requestMatchers("/inicio/", "/css/**", "/js/**", "/img/**", "/", "/vendor/**", "/productos/obtenerproductoporcategoria/**","/registrar","/guardarUsuario")
                  .permitAll();
          auth.requestMatchers("/realizarpedido/**").hasRole("USER");
          auth.anyRequest().authenticated();
          
      })
      .csrf(csrf -> csrf.disable())
      .formLogin(login -> login
              .loginPage("/autenticar")
              .defaultSuccessUrl("/inicio/panel")
              .permitAll())
      .sessionManagement(management -> management
              .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
              .invalidSessionUrl("/autenticar")
              .maximumSessions(1)
              .expiredUrl("/autenticar"))
              
            
      
      .logout(logout -> logout
              .permitAll())
      ;

    return httpSecurity.build();
  }
    
}