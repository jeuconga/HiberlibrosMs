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
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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
                .antMatchers("/hiberlibros/panelUsuario").hasAnyRole("Usuario", "Administrador")
                .antMatchers("/hiberlibros/guardarLibro").hasAnyRole("Usuario", "Administrador")
                .antMatchers("/hiberlibros/saveAutor").hasAnyRole("Usuario", "Administrador")
                .antMatchers("/hiberlibros/registroLibro").hasAnyRole("Usuario", "Administrador")
                .antMatchers("/hiberlibros/buscarLibro").hasAnyRole("Usuario", "Administrador")
                .antMatchers("/hiberlibros/tablaBuscarLibro").hasAnyRole("Usuario", "Administrador")
                .antMatchers("/hiberlibros/guardarRelato").hasAnyRole("Usuario", "Administrador")
                .antMatchers("/hiberlibros/relato").hasAnyRole("Usuario", "Administrador")
                .antMatchers("/hiberlibros/borrarUL").hasAnyRole("Usuario", "Administrador")
                .antMatchers("/hiberlibros/gestionarPeticion").hasAnyRole("Usuario", "Administrador")
                .antMatchers("/hiberlibros/realizarIntercambio").hasAnyRole("Usuario", "Administrador")
                .antMatchers("/hiberlibros/rechazarIntercambio").hasAnyRole("Usuario", "Administrador")
                .antMatchers("/hiberlibros/finIntercambio").hasAnyRole("Usuario", "Administrador")
                .antMatchers("/hiberlibros/editarUsuario").hasAnyRole("Usuario", "Administrador")
                .antMatchers("/hilos/**").hasAnyRole("Usuario", "Administrador")
                .antMatchers("/foros/**").hasAnyRole("Usuario", "Administrador")
                .antMatchers("/libros/addValoracionLibro").hasAnyRole("Usuario", "Administrador")
                .antMatchers("/peticion/**").hasAnyRole("Usuario", "Administrador")
                .antMatchers("/preferencia/**").hasAnyRole("Usuario", "Administrador")
                
                
                
                .antMatchers("/hiberlibros/paneladmin/**").hasRole("Administrador")
                .antMatchers("/autores/listarAdmin").hasRole("Administrador")
                .antMatchers("/librosAutor").hasRole("Administrador")
                .antMatchers("/editarAutor").hasRole("Administrador")
                .antMatchers("/newAutor").hasRole("Administrador")
                .antMatchers("/guardarAutor").hasRole("Administrador")
                .antMatchers("/eliminarAutor").hasRole("Administrador")
                .antMatchers("/editoriales/editoriales").hasRole("Administrador")
                .antMatchers("/editoriales/alta").hasRole("Administrador")
                .antMatchers("/editoriales/eliminarEditorial").hasRole("Administrador")
                .antMatchers("/editoriales/modificacion").hasRole("Administrador")
                .antMatchers("/editoriales/listarAdmin").hasRole("Administrador")
                .antMatchers("/editoriales/editar").hasRole("Administrador") 
                .antMatchers("/libros/libros").hasRole("Administrador")
                .antMatchers("/libros/guardar").hasRole("Administrador")
                .antMatchers("/libros/eliminar").hasRole("Administrador")
                .antMatchers("/libros/modificar").hasRole("Administrador")
                .antMatchers("/libros/listarAdmin").hasRole("Administrador")
                .antMatchers("/libros/guardarAdmin").hasRole("Administrador")
                .antMatchers("/libros/eliminarAdmin").hasRole("Administrador")
                
                
                
                
                
                
                
                
                
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
        int a = 3;
        auth.userDetailsService(validacion).passwordEncoder(passwordEncoder());
    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
