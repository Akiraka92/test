package registration.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import registration.vo.Application;
import registration.vo.Lecture;
import registration.vo.Major;
import registration.vo.Professor;
import registration.vo.Regist;
import registration.vo.Student;
import registration.vo.Syllabus;

public class RegistrationDAO {

	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

	public boolean insertMajor(Major major) throws Exception {
		SqlSession session = null;
		try {
			session = factory.openSession();
			RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);
			if(mapper.insertMajor(major)) {
				session.commit();								
			}
			else {
				session.rollback();
				return false;
			}
		}
//		catch(Exception e) {
//			e.printStackTrace();
//			return false;
//		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return true;
	}
	
	
	public boolean insertStudent(Student student) throws Exception {
		SqlSession session = null;
		try {
			session = factory.openSession();
			RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);
			if (mapper.insertStudent(student)) {
				session.commit();
			} else {
				session.rollback();
				return false;
			}
		}
		// catch(Exception e) {
		// e.printStackTrace();
		// return false;
		// }
		finally {
			if (session != null) {
				session.close();
			}
		}
		return true;
	}
	
	
	public boolean insertProfessor(Professor professor) throws Exception {
		SqlSession session = null;
		try {
			session = factory.openSession();
			RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);
			if (mapper.insertProfessor(professor)) {
				session.commit();
			} else {
				session.rollback();
				return false;
			}
		}
		// catch(Exception e) {
		// e.printStackTrace();
		// return false;
		// }
		finally {
			if (session != null) {
				session.close();
			}
		}
		return true;
	}

	public boolean insertLecture(Lecture lecture) throws Exception {
		SqlSession session = null;
		try {
			session = factory.openSession();
			RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);
			if (mapper.insertLecture(lecture)) {
				session.commit();
			} else {
				session.rollback();
				return false;
			}
		}
		// catch(Exception e) {
		// e.printStackTrace();
		// return false;
		// }
		finally {
			if (session != null) {
				session.close();
			}
		}
		return true;
	}

	public boolean insertSyllabus(Syllabus syllabus) throws Exception {
		SqlSession session = null;
		try {
			session = factory.openSession();
			RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);
			if (mapper.insertSyllabus(syllabus)) {
				session.commit();
			} else {
				session.rollback();
				return false;
			}
		}
		// catch(Exception e) {
		// e.printStackTrace();
		// return false;
		// }
		finally {
			if (session != null) {
				session.close();
			}
		}
		return true;
	}
	
	public boolean deleteSyllabus(Syllabus syllabus) throws Exception {
		SqlSession session = null;
		try {
			session = factory.openSession();
			RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);
			if (mapper.deleteSyllabus(syllabus)) {
				session.commit();
			} else {
				session.rollback();
				return false;
			}
		}
		// catch(Exception e) {
		// e.printStackTrace();
		// return false;
		// }
		finally {
			if (session != null) {
				session.close();
			}
		}
		return true;
	}

	public boolean insertClass(Regist regist) throws Exception {
		SqlSession session = null;
		try {
			session = factory.openSession();
			RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);
			if (mapper.insertClass(regist)) {
				session.commit();
			} else {
				session.rollback();
				return false;
			}
		}
		// catch(Exception e) {
		// e.printStackTrace();
		// return false;
		// }
		finally {
			if (session != null) {
				session.close();
			}
		}
		return true;
	}
	
	public boolean updateClass(Regist regist) throws Exception {
		SqlSession session = null;
		try {
			session = factory.openSession();
			RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);
			if (mapper.updateClass(regist)) {
				session.commit();
			} else {
				session.rollback();
				return false;
			}
		}
		// catch(Exception e) {
		// e.printStackTrace();
		// return false;
		// }
		finally {
			if (session != null) {
				session.close();
			}
		}
		return true;
	}
	
	public boolean deleteClass(int class_no) throws Exception {
		SqlSession session = null;
		try {
			session = factory.openSession();
			RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);
			if(mapper.deleteClass(class_no)) {
				session.commit();
			}
			else {
				session.rollback();
				return false;
			}
					
		}
//		catch(Exception e) {
//			e.printStackTrace();
//			return false;
//		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		return true;
	}

	public Student logInQuaryStudent(Student student) throws Exception {
		SqlSession session = null;
		Student s = null;
		try {
			session = factory.openSession();
			RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);
			s = mapper.logInQuaryStudent(student);
		}
		// catch(Exception e) {
		// e.printStackTrace();
		// return false;
		// }
		finally {
			if (session != null) {
				session.close();
			}
		}
		return s;
	}

//	public Regist searchSchedule(Regist regist) throws Exception {
//		SqlSession session = null;
//		Regist s = null;
//		try {
//			session = factory.openSession();
//			RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);
//			s = mapper.searchSchedule(regist);
//		}
//		// catch(Exception e) {
//		// e.printStackTrace();
//		// return false;
//		// }
//		finally {
//			if (session != null) {
//				session.close();
//			}
//		}
//		return s;
//	}

	public ArrayList<Regist> classList(HashMap<String, String> map) {
		SqlSession session = null;
		ArrayList<Regist> list = null;

		try {
			session = factory.openSession();
			RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);
			list = mapper.classList(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;
	}
	
	public Regist selectRegist(int class_no) {
		SqlSession session = null;
		Regist regist = null;

		try {
			session = factory.openSession();
			RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);
			regist = mapper.selectRegist(class_no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return regist;
	}
	
	public ArrayList<Regist> showApplication(String id) {
		SqlSession session = null;
		ArrayList<Regist> list = null;

		try {
			session = factory.openSession();
			RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);
			list = mapper.showApplication(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;
	}

	public Professor logInQuaryProfessor(Professor professor) throws Exception {
		SqlSession session = null;
		Professor p = null;
		try {
			session = factory.openSession();
			RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);
			p = mapper.logInQuaryProfessor(professor);

		}
		// catch(Exception e) {
		// e.printStackTrace();
		// return false;
		// }
		finally {
			if (session != null) {
				session.close();
			}
		}
		return p;
	}
	
	public boolean checkMajor(String major) {
		SqlSession session = null;
		int n = 0;
		try {
			session = factory.openSession();
			RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);
			n = mapper.checkMajor(major);
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		if(n != 0) {
			return true;
		}
		return false;
	}
	
	public boolean checkTutor(Professor professor) {
		SqlSession session = null;
		int n = 0;
		try {
			session = factory.openSession();
			RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);
			n = mapper.checkTutor(professor);
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		if(n != 0) {
			return true;
		}
		return false;
	}
	
	
	public boolean insertApplication(Application application) throws Exception {
		SqlSession session = null;
		try {
			session = factory.openSession();
			RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);
			if(mapper.insertApplication(application)) {
				session.commit();
			}
			else {
				session.rollback();
				return false;
			}
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		return true;
	}
	
	public boolean deleteApplication(Application application) throws Exception {
		SqlSession session = null;
		try {
			session = factory.openSession();
			RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);
			if(mapper.deleteApplication(application)) {
				session.commit();
			}
			else {
				session.rollback();
				return false;
			}
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		return true;
	}

	public Lecture selectLecture(int lecture_id) {
		SqlSession session = null;
		Lecture lecture =null;
		try {
			session = factory.openSession();
			RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);
			lecture = mapper.selectLecture(lecture_id);
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		return lecture;
	}
	
	
	//selectsyllabus DAO
	public Syllabus selectSyllabus(int class_no) {
		SqlSession session = null;
		Syllabus s = null;
		try {
			session = factory.openSession();
			RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);
			s = mapper.selectSyllabus(class_no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return s;
	}
	
	public ArrayList<Lecture> showLecture(String major) {
		SqlSession session = null;
		ArrayList<Lecture> list = null;

		try {
			session = factory.openSession();
			RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);
			list = mapper.showLecture(major);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;
	}


	
}
