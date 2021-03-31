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
		//AutoClosable �������̽��� �������� �Ѵ�
		//�� ä���� try with resources ���� ���� �� �ִ�.
		try(FileWriter fw = new FileWriter("out2.txt")) {
			
			out = new PrintWriter(fw);
			out.println("Hello");
			System.out.println("�۾�����....");
//			out.close();
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
