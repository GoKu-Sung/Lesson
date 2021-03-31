package error;

import java.io.IOException;

public class ExceptionTest {

	public static void main(String[] args) {
		try {
			System.out.println(readString());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
	
	private static String readString() throws IOException{
		byte[] buf = new byte[100];
		System.out.println("문자열 입력");
		System.in.read(buf);
		return new String(buf);
	}
}
