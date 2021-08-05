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

			System.out.println("��¶�q���}�l ��x�O�� �I�s��k class:" + dtypename);
			System.out.println("��¶�q���}�l ��x�O�� �I�s��k �W:" + singname);
			long start = System.currentTimeMillis();

			// ����^�Ѽ� �h�ݪ�^��
			obj = joinpoint.proceed();

			long end = System.currentTimeMillis();
			System.out.println("�`�@����ɪ�" + (end - start) + " �@��");
			System.out.println("��¶�q������ ��x�O��");
		} catch (Throwable t) {
			t.printStackTrace();
			System.out.println("�X�{���~" + t);
		}

		return obj;
	}
}
