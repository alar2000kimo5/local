package appMain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(name="my.ioc.es" ,havingValue = "false")
@Component
public class myinterface_empt implements myInterface {

	@Value("${my.ioc.config}")
	private boolean config;

	@Value("${my.ioc.getstring}")
	private String getstring;

	@Value("${my.ioc.getint}")
	private int getint;

	public myinterface_empt() {

	}

	public void init() {
		System.out.println(config);
		System.out.println(getstring);
		System.out.println(getint);
		System.out.println(" 使用 sit 的連線");
		System.out.println(" empt");
	}
}
