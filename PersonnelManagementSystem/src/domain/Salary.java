package domain;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Salary
{
	public Salary(int id, int baseSalary, int overtimePay, int carAllowance, int houseAllowance, int pension, int medicalInsurance, String time, Staff staff) {
		this.id = id;
		this.baseSalary = baseSalary;
		this.overtimePay = overtimePay;
		this.carAllowance = carAllowance;
		this.houseAllowance = houseAllowance;
		this.pension = pension;
		this.medicalInsurance = medicalInsurance;
		this.time = time;
		this.staff = staff;
	}

	public int getId() {
		return id;
	}

	public int getBaseSalary() {
		return baseSalary;
	}

	public int getOvertimePay() {
		return overtimePay;
	}

	public int getCarAllowance() {
		return carAllowance;
	}

	public int getHouseAllowance() {
		return houseAllowance;
	}

	public int getPension() {
		return pension;
	}

	public int getMedicalInsurance() {
		return medicalInsurance;
	}

	public String getTime() {
		return time;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBaseSalary(int baseSalary) {
		this.baseSalary = baseSalary;
	}

	public void setOvertimePay(int overtimePay) {
		this.overtimePay = overtimePay;
	}

	public void setCarAllowance(int carAllowance) {
		this.carAllowance = carAllowance;
	}

	public void setHouseAllowance(int houseAllowance) {
		this.houseAllowance = houseAllowance;
	}

	public void setPension(int pension) {
		this.pension = pension;
	}

	public void setMedicalInsurance(int medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}

	public void setTime(String time) {
		this.time = time;
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
	
	private int baseSalary;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int overtimePay;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int carAllowance;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int houseAllowance;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int pension;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int medicalInsurance;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String time;

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
	public Salary(){
		super();
	}

}

