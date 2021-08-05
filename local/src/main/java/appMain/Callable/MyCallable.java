package appMain.Callable;

import java.util.concurrent.Callable;

import appMain.myclass.Myclass;

public class MyCallable implements Callable<Myclass> {

	private String name;

	private Myclass myclass;

	public MyCallable(String name) {
		this.name = name;
	}

	public MyCallable(Myclass my) {
		this.myclass = my;
	}

	public Myclass call() throws Exception {
		long threadid = Thread.currentThread().getId();
		System.out.println("call thread id :" + threadid + " processing...");
		Thread.sleep(0);
		return this.myclass;
	}

	public String getName() {
		return name;
	}

}
