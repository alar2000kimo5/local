package appMain.myclass;

public class mynew {

	public static void main(String[] args) throws InterruptedException {

		do {
			try {
				// ����
				System.out.println("����");
				for (int i = 0; i < 10; i++) {
					System.out.println("�]:" + i);
					if (i == 3) {
						throw new NullPointerException();
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			Thread.sleep(3000);
			System.out.println("�~�򰵨�");
			//��������
		} while (true);

	}
}
