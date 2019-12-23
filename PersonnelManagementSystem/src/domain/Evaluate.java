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
		this.assessmentStartDate = assessmentStartDate;
		this.assessmentEndDate = assessmentEndDate;
		this.professionalSkill = professionalSkill;
		this.workAttitude = workAttitude;
		this.workPerformance = workPerformance;
		this.staff = staff;
		this.comment=comment;
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
//	public Evaluate(int id, String assessmentStartDate, String assessmentEndDate, String professionalSkill, String workAttitude, String wordPerformance, Staff staff_id, String comment){
//		super();
//	}

}

