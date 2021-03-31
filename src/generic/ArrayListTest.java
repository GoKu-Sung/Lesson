package generic;

import java.util.*;

public class ArrayListTest {

	public static void main(String[] args) {
		ArrayList<String> strList = new ArrayList();

		strList.add("快蜡");
		strList.add("快蜡1");
		strList.add("快蜡2");
		strList.add("快蜡3");
		strList.add("快蜡4");
		strList.add("快蜡5");
	
		for(int i = 0; i<strList.size(); i++) {
			System.out.println(strList.get(i));
		}
	}

}
