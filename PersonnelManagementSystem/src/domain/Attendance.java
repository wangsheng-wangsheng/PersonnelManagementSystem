package domain;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Attendance
{
	public Attendance(int id, String attendenceTime, String remarks, Staff staff) {
		this.id = id;
		this.attendenceTime = attendenceTime;
		this.remarks = remarks;
		this.staff = staff;
	}

	public int getId() {
		return id;
	}

	public String getAttendenceTime() {
		return attendenceTime;
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

	public void setAttendenceTime(String attendenceTime) {
		this.attendenceTime = attendenceTime;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	
	private String attendenceTime;

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
	public Attendance(){
		super();
	}

}

