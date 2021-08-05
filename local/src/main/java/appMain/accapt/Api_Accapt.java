package appMain.accapt;

import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Api_Accapt {

	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object aroundLog(ProceedingJoinPoint joinpoint) {
		Object obj = null;
		try {
			Signature sign = joinpoint.getSignature();
			String dtypename = sign.getDeclaringTypeName();
			String singname = sign.getName();

			System.out.println("環繞通知開始 日誌記錄 呼叫方法 class:" + dtypename);
			System.out.println("環繞通知開始 日誌記錄 呼叫方法 名:" + singname);
			long start = System.currentTimeMillis();

			// 有返回參數 則需返回值
			obj = joinpoint.proceed();

			long end = System.currentTimeMillis();
			System.out.println("總共執行時長" + (end - start) + " 毫秒");
			System.out.println("環繞通知結束 日誌記錄");
		} catch (Throwable t) {
			t.printStackTrace();
			System.out.println("出現錯誤" + t);
		}

		return obj;
	}
}
