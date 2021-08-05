package appMain.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import appMain.Callable.MyCallable;
import appMain.myclass.Myclass;

@RestController
@RequestMapping(value = "/process")
public class ProcessController {
	ExecutorService threads = Executors.newFixedThreadPool(5);

	@GetMapping(value = "/processStart")
	public String doProcess(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession)
			throws InterruptedException, ExecutionException {
		int i = 0;
		do {

			FutureTask<Myclass> futureTask1 = new FutureTask<Myclass>(new MyCallable(String.valueOf(i)));
			threads.execute(futureTask1);
			Myclass getpei = futureTask1.get();
			System.out.println(getpei.getPei());
			i++;
		} while (i < 100);

		return "thread id : " + String.valueOf(i) + " processing";
	}
}
