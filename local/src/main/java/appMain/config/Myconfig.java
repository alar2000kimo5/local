package appMain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import appMain.Handler.Test_Handler;
import appMain.service.Myutil;

@Configuration
@ComponentScan(value = "appMain")
public class Myconfig {

	@Bean
	public Myutil myutil() {
		return new Myutil();
	}
	
}
