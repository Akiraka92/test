package registration.vo;

public class Professor extends User{
		
	public Professor(String id, String name, String password, String major) {
		super(id, name, password, major);
		
	}
	public Professor() {
		super();
		
		// TODO Auto-generated constructor stub
	}
	
	public Professor(String id, String password) {
		super(id, password);
		
	}
	
	
	
	
}
