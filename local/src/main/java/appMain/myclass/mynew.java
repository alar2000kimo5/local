package appMain.myclass;

public class mynew {

	public static void main(String[] args) throws InterruptedException {

		do {
			try {
				// 做事
				System.out.println("做事");
				for (int i = 0; i < 10; i++) {
					System.out.println("跑:" + i);
					if (i == 3) {
						throw new NullPointerException();
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			Thread.sleep(3000);
			System.out.println("繼續做事");
			//重取任務
		} while (true);

	}
}
