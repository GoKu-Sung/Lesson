package error;

import java.util.Scanner;

public class AssertTest {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("��¥�ӷ�");
		int date = input.nextInt();
		
		assert (date >=1 && date <=31) : "�߸��� ��¥" + date;
		
		System.out.printf("�Էµ� ��¥�� %d �Դϴ� \n", date);
	}
}
