package appMain.controller;

//@PropertySource("classpath:application.properties")
//@Component
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
