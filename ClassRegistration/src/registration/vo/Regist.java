package registration.vo;

public class Regist {
	private int class_no;
	private int lecture_id;
	private String professor_id;
	private String schedule_day;
	private int schedule_start;
	private int schedule_end;
	private int quota;
	private int applicant;
	private String lecture_name;
	private int credit;
	private String lecture_major;
	private String lecture_classify;

	public void setLecture_classify(String lecture_classify) {
		this.lecture_classify = lecture_classify;
	}

	private String professor_name;

	public Regist() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Regist(int class_no, int lecture_id, String professor_id, String schedule_day, int schedule_start,
			int schedule_end, int quota, int applicant) {
		super();
		this.class_no = class_no;
		this.lecture_id = lecture_id;
		this.professor_id = professor_id;
		this.schedule_day = schedule_day;
		this.schedule_start = schedule_start;
		this.schedule_end = schedule_end;
		this.quota = quota;
		this.applicant = applicant;
	}

	public Regist(int lecture_id, String professor_id, String schedule_day, int schedule_start, int schedule_end,
			int quota) {
		super();
		this.lecture_id = lecture_id;
		this.professor_id = professor_id;
		this.schedule_day = schedule_day;
		this.schedule_start = schedule_start;
		this.schedule_end = schedule_end;
		this.quota = quota;
		this.applicant = 0;
	}

	public int getClass_no() {
		return class_no;
	}

	public void setClass_no(int class_no) {
		this.class_no = class_no;
	}

	public int getLecture_id() {
		return lecture_id;
	}

	public void setLecture_id(int lecture_id) {
		this.lecture_id = lecture_id;
	}

	public String getProfessor_id() {
		return professor_id;
	}

	public void setProfessor_id(String professor_id) {
		this.professor_id = professor_id;
	}

	public String getSchedule_day() {
		return schedule_day;
	}

	public void setSchedule_day(String schedule_day) {
		this.schedule_day = schedule_day;
	}

	public int getSchedule_start() {
		return schedule_start;
	}

	public void setSchedule_start(int schedule_start) {
		this.schedule_start = schedule_start;
	}

	public int getSchedule_end() {
		return schedule_end;
	}

	public void setSchedule_end(int schedule_end) {
		this.schedule_end = schedule_end;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public int getApplicant() {
		return applicant;
	}

	public void setApplicant(int applicant) {
		this.applicant = applicant;
	}

	public String getLecture_name() {
		return lecture_name;
	}

	public void setLecture_name(String lectur_name) {
		this.lecture_name = lectur_name;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getProfessor_name() {
		return professor_name;
	}

	public void setProfessor_name(String professor_name) {
		this.professor_name = professor_name;
	}

	public String getLecture_major() {
		return lecture_major;
	}

	public void setLecture_major(String lecture_major) {
		this.lecture_major = lecture_major;
	}

	public String getLecture_classify() {
		return lecture_classify;
	}

	@Override
	public String toString() {
		return "Regist [class_no=" + class_no + ", lecture_id=" + lecture_id + ", professor_id=" + professor_id
				+ ", schedule_day=" + schedule_day + ", schedule_start=" + schedule_start + ", schedule_end="
				+ schedule_end + ", quota=" + quota + ", applicant=" + applicant + ", lecture_name=" + lecture_name
				+ ", credit=" + credit + ", lecture_major=" + lecture_major + ", lecture_classify=" + lecture_classify
				+ ", professor_name=" + professor_name + "]";
	}
	
	
	
}
