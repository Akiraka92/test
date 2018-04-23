package registration.vo;

public class Syllabus {

	private String professor;
	private int lecture_id;
	private String content;
	
	private String professor_name;
	private String lecture_name;
	
	
	public String getProfessor_name() {
		return professor_name;
	}
	public void setProfessor_name(String professor_name) {
		this.professor_name = professor_name;
	}
	public String getLecture_name() {
		return lecture_name;
	}
	public void setLecture_name(String lecture_name) {
		this.lecture_name = lecture_name;
	}
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	public int getLecture_id() {
		return lecture_id;
	}
	public void setLecture_id(int lecture_id) {
		this.lecture_id = lecture_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Syllabus(String professor, int lecture_id, String content) {
		super();
		
		this.professor = professor;
		this.lecture_id = lecture_id;
		this.content = content;
	}
	
	public Syllabus() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
