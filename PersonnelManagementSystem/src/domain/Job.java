package domain;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Job
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
	
	private String remarks;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Department department;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Job(){
		super();
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

	public Department getDepartment() {
		return department;
	}

	public Job(int id, String name, String remarks, Department department) {
		this.id = id;
		this.name = name;
		this.remarks = remarks;
		this.department = department;
	}

	public Job(String name, String remarks, Department department) {
		this.name = name;
		this.remarks = remarks;
		this.department = department;
	}

	public Job(int id, String name, String remarks) {
		this.id = id;
		this.name = name;
		this.remarks = remarks;
	}
}

