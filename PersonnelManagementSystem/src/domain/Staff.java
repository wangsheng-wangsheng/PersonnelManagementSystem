package domain;
import domain.empower.User;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Staff implements Comparable<Staff>
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private User user;
	private int id;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String no;

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

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Staff(){
		super();
	}

	public Staff(User user, int id, String no, String name, String sex,
				 String idCard, String nativePlace, String phoneNumber,
				 String mail, Contract contract, Job job, Education education) {
		this.user = user;
		this.id = id;
		this.no = no;
		this.name = name;
		this.sex = sex;
		this.idCard = idCard;
		this.nativePlace = nativePlace;
		this.phoneNumber = phoneNumber;
		this.mail = mail;
		this.contract = contract;
		this.job = job;
		this.education = education;
	}

	public User getUser() {
		return user;
	}

	public int getId() {
		return id;
	}

	public String getNo() {
		return no;
	}

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

	public void setUser(User user) {
		this.user = user;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNo(String no) {
		this.no = no;
	}

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
	public int compareTo(Staff o) {
		return this.id - o.id;
	}


	@Override
	public String toString() {
		return "Staff{" +
				"user=" + user +
				", id=" + id +
				", no='" + no + '\'' +
				", name='" + name + '\'' +
				", sex='" + sex + '\'' +
				", idCard='" + idCard + '\'' +
				", nativePlace='" + nativePlace + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", mail='" + mail + '\'' +
				", contract=" + contract +
				", job=" + job +
				", education=" + education +
				'}';
	}
}

