package error;

import java.util.Scanner;

public class AssertTest {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("날짜임력");
		int date = input.nextInt();
		
		assert (date >=1 && date <=31) : "잘못된 날짜" + date;
		
		System.out.printf("입력된 날짜는 %d 입니다 \n", date);
	}
}
