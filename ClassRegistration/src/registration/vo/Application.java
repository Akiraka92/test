package registration.vo;

public class Application {
	private int class_no;
	private String student_id;
	
//	public Application() {
//		super();
//		// TODO Auto-generated constructor stub
//	}

	public Application(int class_no, String student_id) {
		super();
		this.class_no = class_no;
		this.student_id = student_id;
	}

	public int getClass_no() {
		return class_no;
	}

	public void setClass_no(int class_no) {
		this.class_no = class_no;
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	@Override
	public String toString() {
		return "강좌번호 : " + class_no + ", 학생 ID : " + student_id;
	}
	
	
	
}
