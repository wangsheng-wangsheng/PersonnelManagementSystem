package domain;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Contract
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
	
	private String startTime;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String endTime;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int signTimes;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String signStatus;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String ifWork;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Contract(){
		super();
	}

	public Contract(int id, String startTime, String endTime, int signTimes, String signStatus, String ifWork) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.signTimes = signTimes;
		this.signStatus = signStatus;
		this.ifWork = ifWork;
	}

	public Contract(String startTime, String endTime, int signTimes, String signStatus, String ifWork) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.signTimes = signTimes;
		this.signStatus = signStatus;
		this.ifWork = ifWork;
	}

	public int getId() {
		return id;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public int getSignTimes() {
		return signTimes;
	}

	public String getSignStatus() {
		return signStatus;
	}

	public String getIfWork() {
		return ifWork;
	}
}

