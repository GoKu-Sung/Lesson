package generic;

public class BoxTest {
	public static void main(String[] args) {
//		Box box = new Box();
//		
//		box.setContent("동해물");
//		String cont = box.getContent();
//		
//		System.out.println(cont);
//		
//		Box2 box2 = new Box2();
//		
//		box2.setContent(3);
//		Integer cont2 = box2.getContent();
//		
//		System.out.println(cont2);

		GenericBox<String> box = new GenericBox();
		box.setContent("동해물");
		String cont = box.getContent();
		System.out.println(cont);
		
		GenericBox<Integer> box2 = new GenericBox();
		box2.setContent(3);
		Integer cont2 = box2.getContent();
		System.out.println(cont2);	
	}
}
