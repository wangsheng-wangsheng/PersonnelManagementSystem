package domain;
/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
public class Department
{
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
	
	private String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private String address;

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
	public Department(){
		super();
	}

	public Department(int id, String name, String address, String remarks, Staff staff) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.remarks = remarks;
		this.staff = staff;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getRemarks() {
		return remarks;
	}

	public Staff getStaff() {
		return staff;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
}

