package com.hiberlibros.HiberLibros.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
 
    @Autowired
    private UserDetailsService validacion;    
    
    //Aquí se configura el acceso
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

//              inicio nuevas seguridades
               .antMatchers("*.js").permitAll()
               .antMatchers("*.css").permitAll()
               .antMatchers("*.jpg").permitAll()
               .antMatchers("*.png").permitAll()
               .antMatchers("/hiberlibros/login").permitAll()
               .antMatchers("/hiberlibros/panelUsuario/**").hasAnyRole("Usuario","Administrador")
               .antMatchers("/hiberlibros/autores/**").hasRole("Usuario")
               .antMatchers("/hiberlibros/editoriales/**").hasRole("Usuario")
               .antMatchers("/hiberlibros/principal/**").hasRole("Usuario")
               .antMatchers("/hiberlibros/paneladmin/**").hasRole("Administrador")

               .antMatchers("/admin/info").permitAll() 
               .antMatchers("/admin/url1").hasAnyRole("Administrador", "Usuario")
          .and()
            .formLogin()
                .loginPage("/hiberlibros")
                .permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
          .and()
            .logout()
          .and()
            .csrf().disable();


    }
    
    
    

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    //Aquí se configura Usuario/Password
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        
        /*auth.inMemoryAuthentication()
                 .withUser("jorge").password("{noop}1111").roles("Usuario")
                 .and()
                 .withUser("juan").password("{noop}1111").roles("Administrador");*/
        int a=3;
        auth.userDetailsService(validacion).passwordEncoder(passwordEncoder());
    }
    
    
    @Bean(name="passwordEncoder")
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    

    
}
