package com.carlos.curso.springboot.app.springbootcrud;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//Sirve para incorporar la nueva carpeta de propiedades
@PropertySource("classpath:messages.properties")
public class AppConfig {

}
