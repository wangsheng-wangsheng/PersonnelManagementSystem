package domain;


import java.sql.Date;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Evaluate
{
	public Evaluate(int id, String assessmentStartDate, String assessmentEndDate, String professionalSkill, String workAttitude, String workPerformance, Staff staff,String comment) {
		this.id = id;
		this.assessmentStartDate = assessmentStartDate;//考核开始时间
		this.assessmentEndDate = assessmentEndDate;//考核结束时间
		this.professionalSkill = professionalSkill;//业务水平
		this.workAttitude = workAttitude;//工作态度
		this.workPerformance = workPerformance;//工作业绩
		this.staff = staff;//员工姓名 id
		this.comment=comment;//备注
	}

    public int getId() {
		return id;
	}

	public String getAssessmentStartDate() {
		return assessmentStartDate;
	}

	public String getAssessmentEndDate() {
		return assessmentEndDate;
	}

	public String getProfessionalSkill() {
		return professionalSkill;
	}

	public String getWorkAttitude() {
		return workAttitude;
	}

	public String getWorkPerformance() {
		return workPerformance;
	}
	public String getComment() {
		return comment;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAssessmentStartDate(String assessmentStartDate) {
		this.assessmentStartDate = assessmentStartDate;
	}

	public void setAssessmentEndDate(String assessmentEndDate) {
		this.assessmentEndDate = assessmentEndDate;
	}

	public void setProfessionalSkill(String professionalSkill) {
		this.professionalSkill = professionalSkill;
	}

	public void setWorkAttitude(String workAttitude) {
		this.workAttitude = workAttitude;
	}

	public void setWorkPerformance(String workPerformance) {
		this.workPerformance = workPerformance;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int id;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String assessmentStartDate;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String assessmentEndDate;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String professionalSkill;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String workAttitude;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String workPerformance;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Staff staff;
	private String comment;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @param id
	 * @param assessmentStartDate
	 * @param assessmentEndDate
	 * @param professionalSkill
	 * @param workAttitude
	 * @param wordPerformance
	 * @param staff_id
	 * @param comment
	 */

}

