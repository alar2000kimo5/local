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
		System.out.println(" �ϥ� dev ���s�u");
		System.out.println(" init db ");
	}
	
	public void useme() {
		System.out.println("�ڳQ�ΤF");
	}
}
