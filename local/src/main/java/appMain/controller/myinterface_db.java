package appMain.controller;

//@PropertySource("classpath:application.properties")
//@Component
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
