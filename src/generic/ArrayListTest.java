package generic;

import java.util.*;

public class ArrayListTest {

	public static void main(String[] args) {
		ArrayList<String> strList = new ArrayList();

		strList.add("����");
		strList.add("����1");
		strList.add("����2");
		strList.add("����3");
		strList.add("����4");
		strList.add("����5");
	
		for(int i = 0; i<strList.size(); i++) {
			System.out.println(strList.get(i));
		}
	}

}