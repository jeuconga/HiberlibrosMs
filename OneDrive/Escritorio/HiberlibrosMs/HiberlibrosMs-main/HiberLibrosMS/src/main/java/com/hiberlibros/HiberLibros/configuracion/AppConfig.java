package com.hiberlibros.HiberLibros.configuracion;

//import org.modelmapper.ModelMapper; 
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
//import org.modelmapper.ModelMapper;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}


}
