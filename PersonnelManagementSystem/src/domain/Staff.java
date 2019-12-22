package domain;
/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Staff extends Actor
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
	
	private int staffId;

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
	
	private String sex;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String idCard;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String nativePlace;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String phoneNumber;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String mail;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Contract contract;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Job job;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Education education;

	public Staff(int id, String name, User user, int id1, int staffId, String name1, String sex, String idCard, String nativePlace, String phoneNumber, String mail, Contract contract, Job job, Education education) {
		super(id, name, user);
		this.id = id1;
		this.staffId = staffId;
		this.name = name1;
		this.sex = sex;
		this.idCard = idCard;
		this.nativePlace = nativePlace;
		this.phoneNumber = phoneNumber;
		this.mail = mail;
		this.contract = contract;
		this.job= job;
		this.education = education;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */


	@Override
	public void setId(int id) {
		this.id = id;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

	@Override
	public int getId() {
		return id;
	}

	public int getStaffId() {
		return staffId;
	}

	@Override
	public String getName() {
		return name;
	}

	public String getSex() {
		return sex;
	}

	public String getIdCard() {
		return idCard;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getMail() {
		return mail;
	}

	public Contract getContract() {
		return contract;
	}

	public Job getJob() {
		return job;
	}

	public Education getEducation() {
		return education;
	}
}

