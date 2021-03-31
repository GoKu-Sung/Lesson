package error;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileError2 {
	public static void main(String[] args) {
		writeList();
	}
	
	private static void writeList() {
		PrintWriter out = null;
		//AutoClosable 인터페이스를 구현해햐 한다
		//그 채겣는 try with resources 절에 사용될 수 있다.
		try(FileWriter fw = new FileWriter("out2.txt")) {
			
			out = new PrintWriter(fw);
			out.println("Hello");
			System.out.println("작업종료....");
//			out.close();
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
