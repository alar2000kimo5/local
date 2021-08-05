package appMain.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(value = "my.ioc.es" ,havingValue = "es")
@Component
public class myinterface_db implements myInterface {

	public myinterface_db() {
		init();
	}

	public void init() {
		System.out.println(" 使用 dev 的連線");
		System.out.println(" init db ");
	}
	
	public void useme() {
		System.out.println("我被用了");
	}
}
