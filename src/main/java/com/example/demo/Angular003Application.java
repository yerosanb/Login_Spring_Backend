package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

//import com.google.api.client.util.Value;

//@CrossOrigin
@SpringBootApplication
@PropertySource("classpath:utils_/utils_.properties")
//@EnableJpaRepositories(repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class)
public class Angular003Application {
	
 	@Value("${hhh}")
	private static String hhh;
 	
 	
	public static void main(String[] args) {
		SpringApplication.run(Angular003Application.class, args);
		System.out.println("hellow: " + hhh);
		
		
		
		return;
	}
	
//	@EnableWebMvc 
//	public class WebConfig implements We bMvcConfigurer {
//
//		@Override
//		public void addCorsMappings(CorsRegistry registry) {
//			registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").maxAge(3600L).allowedHeaders("*")
//					.exposedHeaders("Authorization").allowCredentials(true);
//		}
//
//	}
	

}
