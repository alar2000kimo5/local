package appMain.myclass;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Myclass {

	private final double pei = 3.14165;

	public double getPei() {
		return pei;
	}

	public double getCaculate(int cri) {
		return pei * cri * cri;
	}

	public static void main(String[] args) {

		Map mymap = new HashMap<String, String>();
		Object a = mymap.get("1");
		System.out.println(a);

		double ab = round(0.0 / 0.0, 6);
		System.out.println("ab = " + ab);
	}

	public static double round(double v, int scale) {

		if (scale < 0) {

			throw new IllegalArgumentException(

					"The scale must be a positive integer or zero");

		}
		double abc = 0.0;
		System.out.println(abc);

		BigDecimal b = new BigDecimal(abc);

		BigDecimal one = new BigDecimal("1");

		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

	}

}
