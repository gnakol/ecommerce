package fr.insy2s.commerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

   @Bean
	public WebMvcConfigurer getCorsConfiguration () {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/**")
						.allowedOrigins("*")
//						.allowedOrigins("http://localhost:5173")  a modifier lors de la phase finlale pb...
						.allowedMethods("GET","POST","PUT","DELETE")
						.allowedHeaders("*");
			}
		};
	}
}
