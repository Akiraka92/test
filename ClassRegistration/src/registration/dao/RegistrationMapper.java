package registration.dao;

import java.util.ArrayList;
import java.util.HashMap;

import registration.vo.Application;
import registration.vo.Lecture;
import registration.vo.Major;
import registration.vo.Professor;
import registration.vo.Regist;
import registration.vo.Student;
import registration.vo.Syllabus;
import registration.vo.User;

public interface RegistrationMapper {
	
	public boolean insertMajor(Major major);

	public boolean insertLecture(Lecture lecture);
	
	public boolean insertClass(Regist regist);
	
	public boolean updateClass(Regist regist);
	
	public boolean deleteClass(int class_no);
	
	public boolean insertSyllabus(Syllabus syllabus);
	
	public Student logInQuaryStudent(Student student);
	
	public Professor logInQuaryProfessor(Professor professor);
	
//	public Regist searchSchedule(Regist regist);

	public ArrayList<Regist> classList(HashMap<String, String> map);
	
	public Regist selectRegist(int class_no);
	
	public ArrayList<Regist> showApplication(String id);

	public boolean insertStudent(Student student);
	
	public boolean insertProfessor(Professor professor);
	
	public int checkMajor(String major);
	
	public int checkTutor(Professor professor);
	
	public boolean insertApplication(Application application);
	
	public boolean deleteApplication(Application application);
	
	public Lecture selectLecture(int lecture_id);
	
	public boolean deleteSyllabus(Syllabus syllabus);
	
	public Syllabus selectSyllabus(int class_no);
	
	public ArrayList<Lecture> showLecture(String major);

}
