package generic;

public class ObjectBox { //generic이전에는 Object로 받아서 모든 객체를 처리했지만 이곳 저곳에서 코드를 받아오다 보면 그 코드가 무슨 객체형인지 알 수 없는 상황이 생경을 경우 처리하기가 힘들기 때문에 generic이 나옴
	private Object content;

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
}
