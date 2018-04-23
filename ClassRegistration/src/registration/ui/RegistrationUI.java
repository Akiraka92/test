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
			System.out.println("권한 레벨 : " + permit);
			//
			

			switch (permit) {

			case NOT_CONN: // 초기 화면 // 유저 등록, 로그인, 프로그램 종료의 메뉴를 표시

				printFirstMenu();
				
				int n = getNextInt("");
				if (n == 1) {
					joinMembership();
				}
				else if (n == 2) {
					// 로그인 함수 표시
					logInMenu();
					System.out.println(user);
					
					// 로그인 함수 표시
				} else if (n == 9) {
					System.out.println("프로그램 종료");
					System.exit(0);
				} else {
					System.out.println("잘못된 값이 입력 되었습니다.");
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

		// 수강신청 목록 & 수강과목 입력 System.out.println("List of enrolment & Input subject");
		// 시간표 조회 System.out.println("Timetable check"); // 시간표 출력
		System.out.println("[ 메뉴 ]");
		System.out.println("1. 강좌 조회"); // done
		if(permit == STUDENT) {
			System.out.println("2. 수강 신청"); // done
			System.out.println("3. 신청 내역"); // done 
			System.out.println("4. 신청 삭제"); // done
		}
		else if(permit == PROFESSOR) {
			System.out.println("2. 과목 추가"); // done
			System.out.println("3. 강의 추가"); // done
			System.out.println("4. 강의 삭제"); // done
			
		}
		System.out.println("5. 시간표 출력"); // done
		System.out.println("6. 강의계획서 조회");
		if(permit == PROFESSOR) {
			System.out.println("7. 강의계획서 작성"); // done
			System.out.println("8. 강의계획서 삭제");
		}
		System.out.println("9. 로그아웃"); // done
		System.out.print("메뉴 선택 >> ");
	}
	
	public void mainMenu() {
		int n = getNextInt("");
		switch(n) {
		
		case 1:
			printClassList();
			break;
			
		case 2:
			if(permit == STUDENT) {
				int class_no = getNextInt("강좌번호 : ");
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
			int class_no = getNextInt("강좌번호 : ");
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
			// 강의계획서 조회
			//강의 번호를 입력 받아서 강의 계획서 쿼리,
			// 과목명, 교수명, 내용을 화면 표시
			// 추가기능 : 강의계획서 액셀 저장
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
				// 강의계획서 작성
				// 강의 번호를 입력 받는데, 본인(교수)의 과목이 아닐 경우 작성 불가
				// (user.getId() 이용...)
				// 추가기능 : update. 즉, 강의계획서 수정 기능과 delete 삭제 기능				
				break;
			}
			
		case 8:
			if(permit == PROFESSOR) {
				deleteSyllabus();
				break;
			}
		default:
			System.out.println("잘못된 입력");
			break;
		}
	}

	public void printAdminMenu() {

		// 학생 시간표 검색 System.out.println("Timetable check"); // 전체학생 조회
		System.out.println(""); // 초기화 System.out.println(""); // 비밀번호 변경
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
			System.out.println("[ 회원 가입 ]");
			System.out.println("( 학생 : 1 || 교직원 :2  [입력하시오] )");
			
			identifier = getNextInt("[입력] : ");
			if(identifier>2) {
				System.out.println("잘못된 입력!");
				input.nextLine();
				break;
			}
			
			String id = null;
			while(id == null) {
				System.out.print("ID : ");
				id = input.next();
				try {
					if((dao.logInQuaryStudent(new Student(id, "signUp"))!=null)||(dao.logInQuaryProfessor(new Professor(id, "signUp"))!=null)) {
						System.out.println("중복된 ID입니다.");
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
				System.out.print("PW 확인 : ");
				if(!input.next().equals(pw)) {
					System.out.println("PW가 일치하지 않습니다.");
					input.nextLine();
					pw = null;
				}
			}
//			System.out.println(pw);
//			System.out.println(pw.length());
			System.out.print("이름 : ");
			String name = input.next();
//			System.out.println(name);
			
			String major = null;
			while(major == null) {				
				System.out.print("전공 : ");
				major = input.next();
				if(!dao.checkMajor(major)) {
					System.out.println("존재하지 않는 전공입니다.");
					input.nextLine();
					major = null;
				}
				
				// test
				// System.out.println(major);
			}
			
			switch (identifier) {
			case 1:// 학생 회원가입페이지
				String tutor = null;
				while(tutor == null) {
					System.out.print("지도교수 ID : ");
					tutor = input.next();
					// test
					// System.out.println(tutor);
					Professor p = new Professor(tutor, null, null, major);
					// test
					// System.out.println(p.getId() + p.getMajor());
					if(!dao.checkTutor(p)) {
						System.out.println("교수 정보가 올바르지 않습니다.");
						input.nextLine();
						tutor = null;
					}
				}
				Student student = new Student(id, name, pw, major, tutor);
				try {
					dao.insertStudent(student);
					System.out.println("학생 등록 완료");
					user = student;
					permit = STUDENT;
				} catch (Exception e) {
					System.out.println("학생 등록 실패");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 2:// 교수 회원가입 페이지
				Professor professor = new Professor(id, pw, name, major);
				try {
					dao.insertProfessor(professor);
					System.out.println("교수 등록 완료");
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

		System.out.println("[ 회원 가입 ]");
		input.nextLine();
		System.out.print("ID : ");
		student_id = input.next();
		while (true) {
			System.out.print("비밀번호 : ");
			student_pw = input.next();
			System.out.print("비밀번호를 다시한번 입력해주세요. ");
			re_pw = input.next();
			if (student_pw.equals(re_pw)) {
				System.out.println("비밀번호가 정상적으로 입력되었습니다.");
				break;
			} else {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}
		}
		System.out.print("이름 : ");
		student_name = input.next();
		System.out.print("전공 : ");
		major = input.next();
		System.out.print("지도 교수 : ");
		tutor = input.next();

		student = new Student(student_id, student_name, student_pw, major, tutor);
		System.out.println("저장 중...");
		try

		{
			if (dao.insertStudent(student)) {
				System.out.println("등록 완료");
			} else {
				System.out.println("등록 실패");
			}
		} catch (Exception e) {
			System.out.println("등록 실패");
		}

	}*/

	// 과목 입력
	public void insertLecture() {
		Lecture lecture;
		int credit;
		String subject, classify, major;
		System.out.println("[ 과목 추가 ]");
		input.nextLine();
		System.out.print("과목명 : ");
		subject = input.next();
		credit =

				getNextInt("이수학점 : ");
		System.out.print("전공 : ");
		major = input.next();
		System.out.print("구분 : ");
		classify = input.next();

		lecture = new Lecture(credit, subject, classify, major);
		System.out.println("저장 중...");

		try {
			if (dao.insertLecture(lecture)) {
				System.out.println("등록 완료");
			} else {
				System.out.println("등록 실패");
			}
		} catch (Exception e) {
			System.out.println("등록 실패 (예외 발생)");
		}
	}

	// 학부 추가 // 사용 안함
	public void insertMajor() {
		Major major;
		String m, b;
		System.out.println("[ 학부 추가 ]");
		input.nextLine();
		System.out.print("학부 이름 : ");
		m = input.next();
		System.out.print("강의동 : ");
		b = input.next();

		major = new Major(m, b);
		System.out.println("저장 중...");
		try

		{
			if (dao.insertMajor(major)) {
				System.out.println("등록 완료");
			} else {
				System.out.println("등록 실패");
			}
		} catch (Exception e) {
			System.out.println("등록 실패");
		}

	}


	// 강의 추가
	public void insertClass() {
		Regist regist;
		String day = null;
		int lec_id = -1, start = -1, end = -2, quota;

		System.out.println("[ 강좌 추가 ]");

		// 교수 로그인된 현재의 ID를 얻어서 입력 받는다.
		// System.out.print("교수 id : ");
		String pro_id = user.getId();

		while(lec_id <= 0) {
			lec_id = getNextInt("과목 번호 : ");
			if(!user.getMajor().equals(dao.selectLecture(lec_id).getMajor())) {
				System.out.println("본인의 과목 번호를 입력하세요.");
				lec_id = -1;
				input.nextLine();
			}
		}
		quota = getNextInt("수강 정원 : ");
		while(true) {
			while (day == null) {
				System.out.print("강의 요일 : ");
				day = input.next();
				if (!(day.equals("mon") || day.equals("tue") || day.equals("wed") || day.equals("thu")
						|| day.equals("fri"))) {
					System.out.println("[에러] mon ~ fri 중에 하나를 입력하세요.");
					input.nextLine();
					day = null;
				}
			}
			while (start < 0) {
				start = getNextInt("시작 교시 : ");
				end = getNextInt("끝 교시 : ");
				if (start > end) {
					System.out.println("[에러] 잘못 입력하였습니다.");
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
							System.out.println("시간표에 중복된 강의가 있습니다.");
							return;
						}
					}
				}
			}
			break;
		}

		regist = new Regist(lec_id, pro_id, day, start, end, quota);
		System.out.println("저장 중...");
		try {
			if (dao.insertClass(regist)) {
				System.out.println("등록 완료");
			} else {
				System.out.println("등록 실패");
			}
		} catch (Exception e) {
			System.out.println("등록 실패 (예외 발생)");
		}

	}
	
	
	// 강의 삭제
	public void deleteClass(int class_no) {
		Application application = new Application(class_no, "drop_class");
		try {
			dao.deleteApplication(application);
			if(dao.deleteClass(class_no)) {
				System.out.println("삭제 완료");
			}
			else {
				System.out.println("삭제 실패");
			}
		}
		catch(Exception e) {
			System.out.println("삭제 실패 (예외 발생)");
		}
	}
	

	public void printClassList() {
		System.out.println("[ 강좌 목록 ]");
		String col = null;
		String text = null;

		System.out.print("* 검색 대상 : 1.전체  2.교수코드  3.과목코드  ");
		if (permit == PROFESSOR) {
			System.out.print("4.내 강의  > ");
		} else if (permit == STUDENT) {
			System.out.print("> ");
		} else {
			System.out.println("권한이 없습니다!!");
			return;
		}
		col = input.next();
		if (permit == PROFESSOR && col.equals("4")) {
			col = "2";

			// 현재 유저(교수)의 ID를 받아와서 입력..
			text = user.getId();
		} else if (col.equals("1")) {
			text = null;
		} else {
			System.out.print("* 검색어 > ");
			input.nextLine(); // 스캐너 비우기
			text = input.nextLine();
		}

		HashMap<String, String> map = new HashMap<>();
		map.put("col", col);
		map.put("text", text);

		ArrayList<Regist> list = dao.classList(map);

		if (list == null || list.size() == 0) {
			System.out.println("검색 결과가 없습니다.");
			return;
		} else {
			for (Regist re : list) {
				if(permit==STUDENT && (!re.getLecture_major().equals(user.getMajor())&&!re.getLecture_classify().equals("general"))) {
					continue;
				}
				System.out.print("강좌번호 : " + re.getClass_no() + "\t");
				System.out.print("과목코드 : " + re.getLecture_id() + "\t");
				System.out.print("과목이름 : " + re.getLecture_name() + "\t");
				System.out.print("이수학점 : " + re.getCredit() + "\t");
				System.out.print("이수구분 : " + re.getLecture_classify() + "\t");
				System.out.print("교수 : " + re.getProfessor_name() + "\t");
				System.out.print("강의시간 : " + re.getSchedule_day() + " ");
				System.out.print(re.getSchedule_start() + " ~ " + re.getSchedule_end() + "교시" +"\t");
				System.out.print("수강인원 : " + re.getApplicant() + "/" + re.getQuota());
				System.out.println();
			}
		}
	}
	
	public void logInMenu() {
		System.out.println("[ 로그인 ]");
		input.nextLine();
		User u, uo;
		System.out.print("ID 입력 : ");
		String logInID = input.next();
		System.out.print("PW 입력 : ");
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
			System.out.println("예외 오류 발생");
			e.printStackTrace();
		} finally {
			// test /////////////////////////////////////
			// System.out.println("권한 레벨 : " + permit);
			/////////////////////////////////////////////
		}

	}

	// 수강신청 삽입
	public void insertApplication(int class_no) {
		if(permit!=STUDENT) {
			return;
		}
		Application application = new Application(class_no, user.getId());
		Regist regist = dao.selectRegist(class_no);

		if(regist.getQuota() <= regist.getApplicant()) {
			System.out.println("인원 초과!!");
			return;
		}
		
		for (Regist r : dao.showApplication(user.getId())) {
			if (regist.getLecture_id() == r.getLecture_id()) {
				System.out.println("이미 신청된 과목입니다.");
				return;
			}
			if (regist.getSchedule_day().equals(r.getSchedule_day())) {
				for(int i = r.getSchedule_start(); i <= r.getSchedule_end(); i++) {
					if(regist.getSchedule_start() == i || regist.getSchedule_end() == i) {
						System.out.println("시간표에 중복된 강의가 있습니다.");
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
			System.out.println("잘못된 접근!!");
		}
	}
	
	// 수강신청 삭제
	public void deleteApplication(int class_no) {
		if (permit != STUDENT) {
			return;
		}
		Application application = new Application(class_no, user.getId());
		Regist regist = dao.selectRegist(class_no);
		if (regist.getClass_no() == class_no) {
			try {
				dao.deleteApplication(application);
				//업데이트함수
				dao.updateClass(regist);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("삭제");
			return;
		}
		else {
			System.out.println("해당 수업 내용이 없습니다.");
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
			System.out.print("강좌번호 : " + r.getClass_no() + "\t");
			System.out.print("과목코드 : " + r.getLecture_id() + "\t");
			System.out.print("과목이름 : " + r.getLecture_name() + "\t");
			System.out.print("이수학점 : " + r.getCredit() + "\t");
			System.out.print("이수구분 : " + r.getLecture_classify() + "\t");
			System.out.print("교수 : " + r.getProfessor_name() + "\t");
			System.out.print("강의시간 : " + r.getSchedule_day() + " ");
			System.out.print(r.getSchedule_start() + " ~ " + r.getSchedule_end() + "교시" +"\t");
			System.out.println();
			credit += r.getCredit();
		}
		System.out.println("총 신청 학점 : " + credit);
		
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
	
	
	// 강의계획서 작성
	public void insertSyllabus() {
		if(permit != PROFESSOR) {
			return;
		}
		
		Syllabus syllabus;
		int lecture_id = -1;
		String content;
		System.out.println("[ 강의계획서 입력 ]");
		boolean flag = false;
		System.out.print("과목 코드 : ");
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
			System.out.println("본인의 과목 코드를 입력하세요.");
			return;
		}
		

		System.out.print("강의계획서 내용 : ");
		content = input.next();

		syllabus = new Syllabus(user.getId(), lecture_id, content);
		System.out.println("저장 중...");

		try {
			if (dao.insertSyllabus(syllabus)) {
				System.out.println("등록 완료");
			} else {
				System.out.println("등록 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("등록 실패 (예외 발생)");
		}
	}

	
	public void deleteSyllabus() {
		if(permit != PROFESSOR) {
			return;
		}
		Syllabus syllabus;
		int lecture_id = -1;
		System.out.println("[ 강의계획서 삭제 ]");
		System.out.print("과목 코드 : ");
		lecture_id = input.nextInt();
		syllabus = new Syllabus(user.getId(), lecture_id, null);
		try {
			if(dao.deleteSyllabus(syllabus)) {
				System.out.println("삭제 완료");
			}
			else {
				System.out.println("삭제 실패");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("삭제 실패 (예외 발생)");
		}
		
	}
	
	//selectSyllabus UI
	public void selectSyllabus() {

		System.out.println("[강의계획서 조회]");
		System.out.print("강좌 번호 : ");                 
		int class_no = input.nextInt();
		

		Syllabus s = dao.selectSyllabus(class_no);
		System.out.print("교수  명 : [" + s.getProfessor_name()+"]   ");
		System.out.println("강의 명 : [" + s.getLecture_name() +"]   ");
		System.out.println("내 용 : " + s.getContent());
			
	}

	
	public void showLecture() {
		ArrayList<Lecture> list = dao.showLecture(user.getMajor());
		for(Lecture l : list) {
			System.out.printf("과목번호 : %5d", l.getLectureId());
			System.out.printf(", 과목명 : %15s", l.getSubject());
			System.out.printf(", 이수학점 : %d", l.getCredit());
			System.out.printf(", 이수구분 : %7s", l.getClassify());
			System.out.printf(", 전공 : %5s\n", l.getMajor());
		}
	}
	

	private int getNextInt(String message) {
		int option = 0;

		do {
			try {
				System.out.print(message);
				option = input.nextInt();
			} catch (Exception e) {
				System.out.println("[에러] 잘못 입력하였습니다");
				input.nextLine();
			}
		} while (option <= 0);

		return option;
	}
}
