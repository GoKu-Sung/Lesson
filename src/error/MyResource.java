package error;

public class MyResource implements AutoCloseable{
	public MyResource() {
		System.out.println("My Resource ����");
		
	}
	
	public int getValue() throws Exception {
		int random = (int)(Math.random()*2);
		if (random == 0) {
//			throw new Exception("�ڿ��� ����");
		}
		return (int)(Math.random()*100);
	}
	
	public void close() {
		System.out.println("�ڿ� �ݳ�");
	}
}
