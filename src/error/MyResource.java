package error;

public class MyResource implements AutoCloseable{
	public MyResource() {
		System.out.println("My Resource 생성");
		
	}
	
	public int getValue() throws Exception {
		int random = (int)(Math.random()*2);
		if (random == 0) {
//			throw new Exception("자원고갈 오류");
		}
		return (int)(Math.random()*100);
	}
	
	public void close() {
		System.out.println("자원 반남");
	}
}
