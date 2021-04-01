package generic;

import java.util.ArrayList;

public class ArrayListTest2<T> {
	private Object[] arr, arr1;
	private int capacity = 10;
	private int size = 0;
	
	public ArrayListTest2() {
		arr = new Object[capacity];
	}

	private void increaseCapacity() {
		if(size >= capacity){
			capacity = capacity + 1;
			arr1 = new Object[capacity];
			for(int i = 0; size > i; i++) {
				arr1[i] = arr[i];
			}
			arr = arr1;	
		}
	}
	
	public void remove() {
		if(size > 0)
			size--;
	}
	
	public void remove(int idx) {
		for (int i = 0; i < size - idx - 1; i++) {
			arr[idx+i] = arr[idx+i+1];
		}
		if(0 <= (size - idx - 1)) {
		size--;
		}else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public void add(T value) {
		increaseCapacity();
		arr[size++] = value;
	}
	
	public void add(int idx, T value) {
		increaseCapacity();
		for (int i = size-1; i >= idx; i--) {
			arr[i+1] = arr[i];
		}
		arr[idx] = value;
		size++;
	}
	
	public int size() {
		return size;
	}
	
	public Object get(int idx) {
		return arr[idx];
	}
	
	@Override
	public String toString() {
		for(int i = 0;i < size; i++) {
			System.out.println(arr[i]);
		}
		return "";
	}
	
	public static void main(String[] args) {
//		ArrayList<Integer> list = new ArrayList();
		ArrayListTest2 list = new ArrayListTest2();
		
		list.add("홍길동"); //제네릭 클래스로 만들기
		
		for(int i = 0;i <= 10; i++) {
			list.add(i+"번");
		}
		
//		list.add(1, 111); 
		list.remove(10); //인덱스 번호로 삭제
		System.out.println(list);

//		list.remove(Integer.valueOf(100)); //오브젝트 형으로 삭제
		
	}
}
