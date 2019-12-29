package domain;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Attendance
{
	
	private int id;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String startTime;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private String overTime;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private String type;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private String remarks;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Staff staff;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Attendance(int id, String startTime, String overTime, String type, String remarks, Staff staff) {
		this.id = id;
		this.startTime = startTime;
		this.overTime = overTime;
		this.type = type;
		this.remarks = remarks;
		this.staff = staff;
	}

	public int getId() {
		return id;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getOverTime() {
		return overTime;
	}

	public String getType() {
		return type;
	}

	public String getRemarks() {
		return remarks;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@Override
	public String toString() {
		return "Attendance{" +
				"id=" + id +
				", startTime='" + startTime + '\'' +
				", overTime='" + overTime + '\'' +
				", type='" + type + '\'' +
				", remarks='" + remarks + '\'' +
				", staff=" + staff +
				'}';
	}
}

