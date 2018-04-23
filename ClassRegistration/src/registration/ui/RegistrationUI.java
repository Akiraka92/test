package registration.ui;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import registration.dao.RegistrationDAO;
import registration.vo.Application;
import registration.vo.Lecture;
import registration.vo.Major;
import registration.vo.Professor;
import registration.vo.Regist;
import registration.vo.Student;
import registration.vo.Syllabus;
import registration.vo.User;

public class RegistrationUI {

	private final int NOT_CONN = 0;
	private final int STUDENT = 1;
	private final int PROFESSOR = 2;
	private int permit = NOT_CONN;
	private User user = null;

	private Scanner input = new Scanner(System.in);

	private RegistrationDAO dao = new RegistrationDAO();

	public RegistrationUI() {

		// test ////////////////////////////

		// logInMenu();
		// insertClass();
		// insertMajor();
		// insertLecture();
		// insertSyllabus();
		// permit = STUDENT;
		// printClassList();

		////////////////////////////////////

		while (true) {
			System.out.println();
			// test
			System.out.println("���� ���� : " + permit);
			//
			

			switch (permit) {

			case NOT_CONN: // �ʱ� ȭ�� // ���� ���, �α���, ���α׷� ������ �޴��� ǥ��

				printFirstMenu();
				
				int n = getNextInt("");
				if (n == 1) {
					joinMembership();
				}
				else if (n == 2) {
					// �α��� �Լ� ǥ��
					logInMenu();
					System.out.println(user);
					
					// �α��� �Լ� ǥ��
				} else if (n == 9) {
					System.out.println("���α׷� ����");
					System.exit(0);
				} else {
					System.out.println("�߸��� ���� �Է� �Ǿ����ϴ�.");
					// continue;
				}
				break;

			case STUDENT: 
			case PROFESSOR:
	
				printMainMenu();
				mainMenu();
				break;

			}
		}
	}

	public void printMainMenu() {

		// ������û ��� & �������� �Է� System.out.println("List of enrolment & Input subject");
		// �ð�ǥ ��ȸ System.out.println("Timetable check"); // �ð�ǥ ���
		System.out.println("[ �޴� ]");
		System.out.println("1. ���� ��ȸ"); // done
		if(permit == STUDENT) {
			System.out.println("2. ���� ��û"); // done
			System.out.println("3. ��û ����"); // done 
			System.out.println("4. ��û ����"); // done
		}
		else if(permit == PROFESSOR) {
			System.out.println("2. ���� �߰�"); // done
			System.out.println("3. ���� �߰�"); // done
			System.out.println("4. ���� ����"); // done
			
		}
		System.out.println("5. �ð�ǥ ���"); // done
		System.out.println("6. ���ǰ�ȹ�� ��ȸ");
		if(permit == PROFESSOR) {
			System.out.println("7. ���ǰ�ȹ�� �ۼ�"); // done
			System.out.println("8. ���ǰ�ȹ�� ����");
		}
		System.out.println("9. �α׾ƿ�"); // done
		System.out.print("�޴� ���� >> ");
	}
	
	public void mainMenu() {
		int n = getNextInt("");
		switch(n) {
		
		case 1:
			printClassList();
			break;
			
		case 2:
			if(permit == STUDENT) {
				int class_no = getNextInt("���¹�ȣ : ");
				insertApplication(class_no);
			}
			else if(permit == PROFESSOR) {
				insertLecture();
			}
			break;
			
		case 3:
			if(permit == STUDENT) {
				showApplication();
			}
			else if(permit == PROFESSOR) {
				showLecture();
				insertClass();
			}
			break;
			
		case 4:
			int class_no = getNextInt("���¹�ȣ : ");
			if(permit == STUDENT) {
				deleteApplication(class_no);
			}
			else if(permit == PROFESSOR) {
				deleteClass(class_no);
			}
			
			break;
			
		case 5:
			showTimeTable();
			break;
			
		case 6:
			// ���ǰ�ȹ�� ��ȸ
			//���� ��ȣ�� �Է� �޾Ƽ� ���� ��ȹ�� ����,
			// �����, ������, ������ ȭ�� ǥ��
			// �߰���� : ���ǰ�ȹ�� �׼� ����
			selectSyllabus();
			
			break;
		
		case 9:
			permit = NOT_CONN;
			user = null;
			System.out.println();
			return;

		case 7:
			if(permit == PROFESSOR) {
				insertSyllabus();
				// ���ǰ�ȹ�� �ۼ�
				// ���� ��ȣ�� �Է� �޴µ�, ����(����)�� ������ �ƴ� ��� �ۼ� �Ұ�
				// (user.getId() �̿�...)
				// �߰���� : update. ��, ���ǰ�ȹ�� ���� ��ɰ� delete ���� ���				
				break;
			}
			
		case 8:
			if(permit == PROFESSOR) {
				deleteSyllabus();
				break;
			}
		default:
			System.out.println("�߸��� �Է�");
			break;
		}
	}

	public void printAdminMenu() {

		// �л� �ð�ǥ �˻� System.out.println("Timetable check"); // ��ü�л� ��ȸ
		System.out.println(""); // �ʱ�ȭ System.out.println(""); // ��й�ȣ ����
		System.out.println("");

	}
	

	public void printFirstMenu() {
		System.out.println("[SMART CLOUD IT MASTER Univ. class registration program ]");
		System.out.println("1. Member Registration");
		System.out.println("2.  LOG-IN");
		System.out.println("9. End of Program");
		System.out.print("* Select Menu Number > ");
	}

	public void joinMembership() {
		int identifier = 0;
		while(true) {
			System.out.println("[ ȸ�� ���� ]");
			System.out.println("( �л� : 1 || ������ :2  [�Է��Ͻÿ�] )");
			
			identifier = getNextInt("[�Է�] : ");
			if(identifier>2) {
				System.out.println("�߸��� �Է�!");
				input.nextLine();
				break;
			}
			
			String id = null;
			while(id == null) {
				System.out.print("ID : ");
				id = input.next();
				try {
					if((dao.logInQuaryStudent(new Student(id, "signUp"))!=null)||(dao.logInQuaryProfessor(new Professor(id, "signUp"))!=null)) {
						System.out.println("�ߺ��� ID�Դϴ�.");
						id = null;
					};
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			String pw = null;
			while(pw == null) {
				System.out.print("PW : ");
				pw = input.next();
				System.out.print("PW Ȯ�� : ");
				if(!input.next().equals(pw)) {
					System.out.println("PW�� ��ġ���� �ʽ��ϴ�.");
					input.nextLine();
					pw = null;
				}
			}
//			System.out.println(pw);
//			System.out.println(pw.length());
			System.out.print("�̸� : ");
			String name = input.next();
//			System.out.println(name);
			
			String major = null;
			while(major == null) {				
				System.out.print("���� : ");
				major = input.next();
				if(!dao.checkMajor(major)) {
					System.out.println("�������� �ʴ� �����Դϴ�.");
					input.nextLine();
					major = null;
				}
				
				// test
				// System.out.println(major);
			}
			
			switch (identifier) {
			case 1:// �л� ȸ������������
				String tutor = null;
				while(tutor == null) {
					System.out.print("�������� ID : ");
					tutor = input.next();
					// test
					// System.out.println(tutor);
					Professor p = new Professor(tutor, null, null, major);
					// test
					// System.out.println(p.getId() + p.getMajor());
					if(!dao.checkTutor(p)) {
						System.out.println("���� ������ �ùٸ��� �ʽ��ϴ�.");
						input.nextLine();
						tutor = null;
					}
				}
				Student student = new Student(id, name, pw, major, tutor);
				try {
					dao.insertStudent(student);
					System.out.println("�л� ��� �Ϸ�");
					user = student;
					permit = STUDENT;
				} catch (Exception e) {
					System.out.println("�л� ��� ����");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 2:// ���� ȸ������ ������
				Professor professor = new Professor(id, pw, name, major);
				try {
					dao.insertProfessor(professor);
					System.out.println("���� ��� �Ϸ�");
					user = professor;
					permit = PROFESSOR;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			default:
				
				break;
			}
			return;
			
		}
	}

	/*public void insertStudent() {
		Student student;
		String student_id, student_pw, re_pw, student_name, major, tutor;

		System.out.println("[ ȸ�� ���� ]");
		input.nextLine();
		System.out.print("ID : ");
		student_id = input.next();
		while (true) {
			System.out.print("��й�ȣ : ");
			student_pw = input.next();
			System.out.print("��й�ȣ�� �ٽ��ѹ� �Է����ּ���. ");
			re_pw = input.next();
			if (student_pw.equals(re_pw)) {
				System.out.println("��й�ȣ�� ���������� �ԷµǾ����ϴ�.");
				break;
			} else {
				System.out.println("��й�ȣ�� �ٽ� �Է����ּ���.");
				continue;
			}
		}
		System.out.print("�̸� : ");
		student_name = input.next();
		System.out.print("���� : ");
		major = input.next();
		System.out.print("���� ���� : ");
		tutor = input.next();

		student = new Student(student_id, student_name, student_pw, major, tutor);
		System.out.println("���� ��...");
		try

		{
			if (dao.insertStudent(student)) {
				System.out.println("��� �Ϸ�");
			} else {
				System.out.println("��� ����");
			}
		} catch (Exception e) {
			System.out.println("��� ����");
		}

	}*/

	// ���� �Է�
	public void insertLecture() {
		Lecture lecture;
		int credit;
		String subject, classify, major;
		System.out.println("[ ���� �߰� ]");
		input.nextLine();
		System.out.print("����� : ");
		subject = input.next();
		credit =

				getNextInt("�̼����� : ");
		System.out.print("���� : ");
		major = input.next();
		System.out.print("���� : ");
		classify = input.next();

		lecture = new Lecture(credit, subject, classify, major);
		System.out.println("���� ��...");

		try {
			if (dao.insertLecture(lecture)) {
				System.out.println("��� �Ϸ�");
			} else {
				System.out.println("��� ����");
			}
		} catch (Exception e) {
			System.out.println("��� ���� (���� �߻�)");
		}
	}

	// �к� �߰� // ��� ����
	public void insertMajor() {
		Major major;
		String m, b;
		System.out.println("[ �к� �߰� ]");
		input.nextLine();
		System.out.print("�к� �̸� : ");
		m = input.next();
		System.out.print("���ǵ� : ");
		b = input.next();

		major = new Major(m, b);
		System.out.println("���� ��...");
		try

		{
			if (dao.insertMajor(major)) {
				System.out.println("��� �Ϸ�");
			} else {
				System.out.println("��� ����");
			}
		} catch (Exception e) {
			System.out.println("��� ����");
		}

	}


	// ���� �߰�
	public void insertClass() {
		Regist regist;
		String day = null;
		int lec_id = -1, start = -1, end = -2, quota;

		System.out.println("[ ���� �߰� ]");

		// ���� �α��ε� ������ ID�� �� �Է� �޴´�.
		// System.out.print("���� id : ");
		String pro_id = user.getId();

		while(lec_id <= 0) {
			lec_id = getNextInt("���� ��ȣ : ");
			if(!user.getMajor().equals(dao.selectLecture(lec_id).getMajor())) {
				System.out.println("������ ���� ��ȣ�� �Է��ϼ���.");
				lec_id = -1;
				input.nextLine();
			}
		}
		quota = getNextInt("���� ���� : ");
		while(true) {
			while (day == null) {
				System.out.print("���� ���� : ");
				day = input.next();
				if (!(day.equals("mon") || day.equals("tue") || day.equals("wed") || day.equals("thu")
						|| day.equals("fri"))) {
					System.out.println("[����] mon ~ fri �߿� �ϳ��� �Է��ϼ���.");
					input.nextLine();
					day = null;
				}
			}
			while (start < 0) {
				start = getNextInt("���� ���� : ");
				end = getNextInt("�� ���� : ");
				if (start > end) {
					System.out.println("[����] �߸� �Է��Ͽ����ϴ�.");
					input.nextLine();
					start = -1;
					end = -2;
				}
			}
			String col = "2";
			String text = user.getId();
			HashMap<String, String> map = new HashMap<>();
			map.put("col", col);
			map.put("text", text);
			ArrayList<Regist> list = dao.classList(map);
			for (Regist r : list) {

				if (r.getSchedule_day().equals(day)) {
					for(int i = r.getSchedule_start(); i <= r.getSchedule_end(); i++) {
						if(start == i || end == i) {
							System.out.println("�ð�ǥ�� �ߺ��� ���ǰ� �ֽ��ϴ�.");
							return;
						}
					}
				}
			}
			break;
		}

		regist = new Regist(lec_id, pro_id, day, start, end, quota);
		System.out.println("���� ��...");
		try {
			if (dao.insertClass(regist)) {
				System.out.println("��� �Ϸ�");
			} else {
				System.out.println("��� ����");
			}
		} catch (Exception e) {
			System.out.println("��� ���� (���� �߻�)");
		}

	}
	
	
	// ���� ����
	public void deleteClass(int class_no) {
		Application application = new Application(class_no, "drop_class");
		try {
			dao.deleteApplication(application);
			if(dao.deleteClass(class_no)) {
				System.out.println("���� �Ϸ�");
			}
			else {
				System.out.println("���� ����");
			}
		}
		catch(Exception e) {
			System.out.println("���� ���� (���� �߻�)");
		}
	}
	

	public void printClassList() {
		System.out.println("[ ���� ��� ]");
		String col = null;
		String text = null;

		System.out.print("* �˻� ��� : 1.��ü  2.�����ڵ�  3.�����ڵ�  ");
		if (permit == PROFESSOR) {
			System.out.print("4.�� ����  > ");
		} else if (permit == STUDENT) {
			System.out.print("> ");
		} else {
			System.out.println("������ �����ϴ�!!");
			return;
		}
		col = input.next();
		if (permit == PROFESSOR && col.equals("4")) {
			col = "2";

			// ���� ����(����)�� ID�� �޾ƿͼ� �Է�..
			text = user.getId();
		} else if (col.equals("1")) {
			text = null;
		} else {
			System.out.print("* �˻��� > ");
			input.nextLine(); // ��ĳ�� ����
			text = input.nextLine();
		}

		HashMap<String, String> map = new HashMap<>();
		map.put("col", col);
		map.put("text", text);

		ArrayList<Regist> list = dao.classList(map);

		if (list == null || list.size() == 0) {
			System.out.println("�˻� ����� �����ϴ�.");
			return;
		} else {
			for (Regist re : list) {
				if(permit==STUDENT && (!re.getLecture_major().equals(user.getMajor())&&!re.getLecture_classify().equals("general"))) {
					continue;
				}
				System.out.print("���¹�ȣ : " + re.getClass_no() + "\t");
				System.out.print("�����ڵ� : " + re.getLecture_id() + "\t");
				System.out.print("�����̸� : " + re.getLecture_name() + "\t");
				System.out.print("�̼����� : " + re.getCredit() + "\t");
				System.out.print("�̼����� : " + re.getLecture_classify() + "\t");
				System.out.print("���� : " + re.getProfessor_name() + "\t");
				System.out.print("���ǽð� : " + re.getSchedule_day() + " ");
				System.out.print(re.getSchedule_start() + " ~ " + re.getSchedule_end() + "����" +"\t");
				System.out.print("�����ο� : " + re.getApplicant() + "/" + re.getQuota());
				System.out.println();
			}
		}
	}
	
	public void logInMenu() {
		System.out.println("[ �α��� ]");
		input.nextLine();
		User u, uo;
		System.out.print("ID �Է� : ");
		String logInID = input.next();
		System.out.print("PW �Է� : ");
		String logInPW = input.next();

		try {
			u = new Student(logInID, logInPW);
			// s= new Student(logInID, logInPW);
			uo = dao.logInQuaryStudent((Student) u);
			if (uo != null) {
				permit = STUDENT;
				user = uo;
				return;
			}
			u = new Professor(logInID, logInPW);
			uo = dao.logInQuaryProfessor((Professor) u);
			if (uo != null) {
				permit = PROFESSOR;
				user = uo;
				return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("���� ���� �߻�");
			e.printStackTrace();
		} finally {
			// test /////////////////////////////////////
			// System.out.println("���� ���� : " + permit);
			/////////////////////////////////////////////
		}

	}

	// ������û ����
	public void insertApplication(int class_no) {
		if(permit!=STUDENT) {
			return;
		}
		Application application = new Application(class_no, user.getId());
		Regist regist = dao.selectRegist(class_no);

		if(regist.getQuota() <= regist.getApplicant()) {
			System.out.println("�ο� �ʰ�!!");
			return;
		}
		
		for (Regist r : dao.showApplication(user.getId())) {
			if (regist.getLecture_id() == r.getLecture_id()) {
				System.out.println("�̹� ��û�� �����Դϴ�.");
				return;
			}
			if (regist.getSchedule_day().equals(r.getSchedule_day())) {
				for(int i = r.getSchedule_start(); i <= r.getSchedule_end(); i++) {
					if(regist.getSchedule_start() == i || regist.getSchedule_end() == i) {
						System.out.println("�ð�ǥ�� �ߺ��� ���ǰ� �ֽ��ϴ�.");
						return;
					}
				}
			}

		}
		
		try {
			//System.out.println(regist);
			dao.insertApplication(application);
			dao.updateClass(regist);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�߸��� ����!!");
		}
	}
	
	// ������û ����
	public void deleteApplication(int class_no) {
		if (permit != STUDENT) {
			return;
		}
		Application application = new Application(class_no, user.getId());
		Regist regist = dao.selectRegist(class_no);
		if (regist.getClass_no() == class_no) {
			try {
				dao.deleteApplication(application);
				//������Ʈ�Լ�
				dao.updateClass(regist);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("����");
			return;
		}
		else {
			System.out.println("�ش� ���� ������ �����ϴ�.");
			return;
		}
	}


	
	public void showApplication() {
		if(permit!=STUDENT) {
			return;
		}
		ArrayList<Regist> list = dao.showApplication(user.getId());
		int credit = 0;
		for(Regist r : list) {
			System.out.print("���¹�ȣ : " + r.getClass_no() + "\t");
			System.out.print("�����ڵ� : " + r.getLecture_id() + "\t");
			System.out.print("�����̸� : " + r.getLecture_name() + "\t");
			System.out.print("�̼����� : " + r.getCredit() + "\t");
			System.out.print("�̼����� : " + r.getLecture_classify() + "\t");
			System.out.print("���� : " + r.getProfessor_name() + "\t");
			System.out.print("���ǽð� : " + r.getSchedule_day() + " ");
			System.out.print(r.getSchedule_start() + " ~ " + r.getSchedule_end() + "����" +"\t");
			System.out.println();
			credit += r.getCredit();
		}
		System.out.println("�� ��û ���� : " + credit);
		
	}
	
	
	public void showTimeTable() {
		String day[] = {"mon", "tue", "wed", "thu", "fri"};
		String[][] timeTable = new String[13][day.length + 1];
		for(int i = 1; i < timeTable.length; i++) {
			timeTable[i][0] = Integer.toString(i);
		}
		for(int i = 1; i < timeTable[0].length; i++) {
			timeTable[0][i] = day[i-1];
		}
		
		if(permit == STUDENT) {
			ArrayList<Regist> list = dao.showApplication(user.getId());
			for(Regist r : list) {
				for(int i = r.getSchedule_start(); i <= r.getSchedule_end(); i++) {
					for(int j = 0; j < day.length; j++) {
						if(day[j].equals(r.getSchedule_day())) {
							timeTable[i][j+1] = r.getLecture_name();
						}
					}
				}
			}
			
		}
		else if(permit == PROFESSOR) {
			String col = "2";
			String text = user.getId();
			HashMap<String, String> map = new HashMap<>();
			map.put("col", col);
			map.put("text", text);
			ArrayList<Regist> list = dao.classList(map);
			for(Regist r : list) {
				for(int i = r.getSchedule_start(); i <= r.getSchedule_end(); i++) {
					for(int j = 0; j < day.length; j++) {
						if(day[j].equals(r.getSchedule_day())) {
							timeTable[i][j+1] = r.getLecture_name();
						}
					}
				}
			}
		}
		else {
			return;
		}
		
		for(int i = 0; i < timeTable.length; i++) {
			for(int j = 0; j < timeTable[i].length; j++) {
				if(timeTable[i][j] == null) {
					System.out.printf("               ");
				}
				else {						
					System.out.printf("%15s", timeTable[i][j]);
				}
			}
			System.out.println();
		}
	}
	
	
	// ���ǰ�ȹ�� �ۼ�
	public void insertSyllabus() {
		if(permit != PROFESSOR) {
			return;
		}
		
		Syllabus syllabus;
		int lecture_id = -1;
		String content;
		System.out.println("[ ���ǰ�ȹ�� �Է� ]");
		boolean flag = false;
		System.out.print("���� �ڵ� : ");
		lecture_id = input.nextInt();
		String col = "3";
		String text = Integer.toString(lecture_id);
		HashMap<String, String> map = new HashMap<>();
		map.put("col", col);
		map.put("text", text);
		ArrayList<Regist> list = dao.classList(map);
		for(Regist r : list) {
			if(r.getProfessor_id().equals(user.getId())) {
				flag = true;
			}
		}
		if(!flag) {
			System.out.println("������ ���� �ڵ带 �Է��ϼ���.");
			return;
		}
		

		System.out.print("���ǰ�ȹ�� ���� : ");
		content = input.next();

		syllabus = new Syllabus(user.getId(), lecture_id, content);
		System.out.println("���� ��...");

		try {
			if (dao.insertSyllabus(syllabus)) {
				System.out.println("��� �Ϸ�");
			} else {
				System.out.println("��� ����");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��� ���� (���� �߻�)");
		}
	}

	
	public void deleteSyllabus() {
		if(permit != PROFESSOR) {
			return;
		}
		Syllabus syllabus;
		int lecture_id = -1;
		System.out.println("[ ���ǰ�ȹ�� ���� ]");
		System.out.print("���� �ڵ� : ");
		lecture_id = input.nextInt();
		syllabus = new Syllabus(user.getId(), lecture_id, null);
		try {
			if(dao.deleteSyllabus(syllabus)) {
				System.out.println("���� �Ϸ�");
			}
			else {
				System.out.println("���� ����");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���� ���� (���� �߻�)");
		}
		
	}
	
	//selectSyllabus UI
	public void selectSyllabus() {

		System.out.println("[���ǰ�ȹ�� ��ȸ]");
		System.out.print("���� ��ȣ : ");                 
		int class_no = input.nextInt();
		

		Syllabus s = dao.selectSyllabus(class_no);
		System.out.print("����  �� : [" + s.getProfessor_name()+"]   ");
		System.out.println("���� �� : [" + s.getLecture_name() +"]   ");
		System.out.println("�� �� : " + s.getContent());
			
	}

	
	public void showLecture() {
		ArrayList<Lecture> list = dao.showLecture(user.getMajor());
		for(Lecture l : list) {
			System.out.printf("�����ȣ : %5d", l.getLectureId());
			System.out.printf(", ����� : %15s", l.getSubject());
			System.out.printf(", �̼����� : %d", l.getCredit());
			System.out.printf(", �̼����� : %7s", l.getClassify());
			System.out.printf(", ���� : %5s\n", l.getMajor());
		}
	}
	

	private int getNextInt(String message) {
		int option = 0;

		do {
			try {
				System.out.print(message);
				option = input.nextInt();
			} catch (Exception e) {
				System.out.println("[����] �߸� �Է��Ͽ����ϴ�");
				input.nextLine();
			}
		} while (option <= 0);

		return option;
	}
}
