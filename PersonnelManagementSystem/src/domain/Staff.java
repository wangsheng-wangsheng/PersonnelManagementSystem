package domain;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Staff extends Actor {
	private String idCard;
	private int id;
	private int staffId;
	private String name;
	private String sex;
	private String nativePlace;
	private String phoneNumber;
	private String mail;
	public Department department;
	public Contract contract;
	public Position position;
	public Education education;

	public Staff(){
		super();
	}
	public Staff(String idCard, int staffId,String name,
				 String sex, String phoneNumber, String mail,
				 String nativePlace, Department department,
				 Contract contract, Position position,
				 Education education){
		super();
		this.idCard = idCard;
		this.staffId = staffId;
		this.sex = sex;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.mail = mail;
		this.nativePlace = nativePlace;
		this.department = department;
		this.contract = contract;
		this.position = position;
		this.education = education;
	}

	public Staff(String idCard, int id, int staffId, String name,
				 String sex, String phoneNumber, String mail,
				 String nativePlace, Department department,
				 Contract contranct, Position position,
				 Education education){
		this(idCard, staffId, name, sex, phoneNumber, mail,
				nativePlace, department, contranct,position, education);
		this.id = id;
	}

	public String getIdCard() {
		return idCard;
	}

	public int getId() {
		return id;
	}

	public int getStaffId() {
		return staffId;
	}

	public String getName() {
		return name;
	}

	public String getSex() {
		return sex;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getMail() {
		return mail;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public Department getDepartment() {
		return department;
	}

	public Contract getContract() {
		return contract;
	}

	public Position getPosition() {
		return position;
	}

	public Education getEducation() {
		return education;
	}


	public String toString()
	{
		final String TAB = "    ";
		String retValue = "STAFF ( "
				+ super.toString() + TAB
				+ "id = " + this.id + TAB
				+ "staffId = " + this.staffId + TAB
				+ "IdCard = " + this.idCard + TAB
				+ "nativePlace = " + this.nativePlace + TAB
				+ "degree = " + this.education + TAB
				+ "department = " + this.department + TAB
				+ " )";
		return retValue;
	}
}
