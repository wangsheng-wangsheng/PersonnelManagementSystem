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
	
	private String remarks;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Leader leader;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Department(int id, String name, String remarks, Leader leader) {
		this.id = id;
		this.name = name;
		this.remarks = remarks;
		this.leader = leader;
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

	public Leader getLeader() {
		return leader;
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

	public void setLeader(Leader leader) {
		this.leader = leader;
	}
}

