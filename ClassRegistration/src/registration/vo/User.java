package registration.vo;

public class User {
	
private String id;
private String name;
private String password;
private String major;



public User() {
	super();
	// TODO Auto-generated constructor stub
}
public User(String id, String name, String password, String major) {
	super();
	this.id = id;
	this.name = name;
	this.password = password;
	this.major = major;
}

public User(String id, String password) {
	super();
	this.id = id;
	this.password = password;
}

@Override
public String toString() {
	return "ID : " + id + ",  이름 : " + name + ",  비밀번호 : " + password + ",  전공 : " + major;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getMajor() {
	return major;
}
public void setMajor(String major) {
	this.major = major;
}



}
