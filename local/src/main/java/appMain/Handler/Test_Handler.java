package appMain.Handler;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class Test_Handler extends HandlerInterceptorAdapter {

	@PostConstruct
	public void init() {
		System.out.println("PostConstruct:" + "init");

	}
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		System.out.println(request);
//		System.out.println(response);
//		System.out.println(request.getRemoteAddr());
//		return false;
//	}

}
