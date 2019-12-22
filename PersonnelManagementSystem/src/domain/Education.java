package domain;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Education
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
	 */

	public void setId(int id) {
		this.id = id;
	}
	public Education(int id, String name){
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
}
	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

