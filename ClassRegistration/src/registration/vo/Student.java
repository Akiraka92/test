package registration.vo;

public class Student extends User {

	private String tutor;



	

	public Student(String id, String name, String password, String major, String tutor) {
		super(id, name, password, major);
		this.tutor = tutor;
	}


	public Student(String tutor) {
		super();
		this.tutor = tutor;
	}

	
	public String getTutor() {
		return tutor;
	}

	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Student(String id, String password) {
		super(id, password);
		
	}


	@Override
	public String toString() {
		return super.toString() +",  지도교수 : " + tutor;
	}


	
	//투스트링 넣어야함
}