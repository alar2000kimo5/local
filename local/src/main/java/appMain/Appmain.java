package appMain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.ConfigurableEnvironment;

@EnableAspectJAutoProxy
@SpringBootApplication
public class Appmain {
	public static void main(String[] args) {
		ConfigurableApplicationContext application = SpringApplication.run(Appmain.class, args);
//		ConfigurableEnvironment environment = application.getEnvironment();
//		int exeMs = environment.getProperty("my.ioc.getint", int.class, 1000);
//		String sdddd = environment.getProperty("my.ioc.getstring", String.class, "isdefalre");
//		boolean cc = environment.getProperty("my.ioc.config", boolean.class, true);
//		System.out.println(exeMs);
//		System.out.println(sdddd);
//		System.out.println(cc);
//		playmymethod();

	}

//	private static void playmymethod() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
//			IllegalArgumentException, InvocationTargetException {
//		Class cls = Class.forName(Appmain.class.getName());
//		cls.newInstance();
//		System.out.println(cls);
//		Myutil my = new Myutil();
//		getClass(my, Myutil.class, (O, S) -> {
//		
//			try {
//				return O.invoke(S, null);
//			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//			return null;
//		});
//
//		//CCscthema ch = new CCscthema();
//		//getClass(ch, CCscthema.class, (a, b) -> "a");
//
//	}
//
//	public void printSchema(Myutil entity) {
//		String myname = entity.getName();
//		System.out.println(myname);
//	}
//
//	private static <T, R> void getClass(T my, T class1, BiFunction<Method, T, R> fun)
//			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
//			InvocationTargetException {
//		T abc = (T) my.getClass();
//		Method[] me = ((Class) abc).getDeclaredMethods();
//		
//		for (int i = 0; i < me.length; i++) {
//			Method mm = me[i];
//			mm.setAccessible(true);
//			//Object retur = mm.invoke(my, null);
//			R abcddd = fun.apply(mm, (T) my);
//			System.out.println(abcddd);
//		}
//		
//		Field[] med = ((Class) abc).getDeclaredFields();
//		
////		for (int i = 0; i < med.length; i++) {
////			Field mm = med[i];
////			mm.setAccessible(true);
////			Object retur = mm.get(my);
////			System.out.println(retur);
////		}
//	}
}