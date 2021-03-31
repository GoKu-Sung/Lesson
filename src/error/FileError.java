package error;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileError {

	public static void main(String[] args) {
		writeList();
	}
	
	private static void writeList() {
		PrintWriter out = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter("C:\\Users\\GoKu\\OneDrive\\바탕 화면\\새 폴더\\java\\Lesson\\out");
			out = new PrintWriter(fw);
			out.println("안녕하세요");
			System.out.println("작업종료....");
			out.close();
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}finally {
			System.out.println("finally code...");
			if (out != null)
				out.close();
			System.out.println("finally end...");
		}
	}

}
